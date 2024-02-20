package io.github.repository.user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.database.AppDatabase
import io.github.domain.user.UserDetailsDomain
import io.github.domain.user.UserDomain
import io.github.domain.user.UserRepositoryInterface
import io.github.network.github.GitHubFeedService
import io.github.repository.toUserDetails
import io.github.repository.toUserDetailsEntity
import io.github.repository.toUserDomainPagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubFeedService: GitHubFeedService,
    private val appDatabase: AppDatabase,
) : UserRepositoryInterface {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(pageSize: Int): Flow<PagingData<UserDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
            ),
            pagingSourceFactory = { appDatabase.userDao().getUsers() },
            remoteMediator = UserItemRemoteMediator(
                userDao = appDatabase.userDao(),
                githubFeedService = githubFeedService,
            )
        ).flow.map { it.toUserDomainPagingData() }
    }

    override suspend fun getUserDetails(userName: String): UserDetailsDomain {
        runCatching {
            val fetchedDetails = githubFeedService.getUserDetails(userName = userName)
            val userDetailsEntity = fetchedDetails.toUserDetailsEntity()
            appDatabase.userDetailsDao().insert(userDetailsEntity)
        }

        return runCatching {
            appDatabase.userDetailsDao().getUser(userName).toUserDetails()
        }.getOrElse {
            throw IllegalStateException("Couldn't get user with username: $userName")
        }
    }
}