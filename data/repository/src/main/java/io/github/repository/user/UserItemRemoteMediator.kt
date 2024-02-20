package io.github.repository.user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.database.user.UserDao
import io.github.database.user.UserEntity
import io.github.network.github.GitHubFeedService
import io.github.repository.toUserEntityList

@OptIn(ExperimentalPagingApi::class)
class UserItemRemoteMediator(
    private val userDao: UserDao,
    private val githubFeedService: GitHubFeedService,
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        val since = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> state.lastItemOrNull()?.id
        } ?: 0

        return runCatching {
            val userItemFeedList = githubFeedService.getUsers(
                since = since,
                perPage = state.config.pageSize,
            )

            userDao.tryDeleteAndInsertAll(
                users = userItemFeedList.toUserEntityList(),
                shouldDelete = loadType == LoadType.REFRESH
            )

            MediatorResult.Success(endOfPaginationReached = userItemFeedList.isEmpty())
        }.getOrElse { MediatorResult.Error(it) }
    }
}