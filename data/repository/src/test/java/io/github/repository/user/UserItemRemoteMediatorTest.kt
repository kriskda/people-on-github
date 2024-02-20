package io.github.repository.user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.database.user.UserDao
import io.github.database.user.UserEntity
import io.github.network.github.GitHubFeedService
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalPagingApi::class)
@RunWith(RobolectricTestRunner::class)
class UserItemRemoteMediatorTest {

    private val mockGitHubFeedService: GitHubFeedService = mock {
        onBlocking { getUsers(any(), any(), any()) } doReturn listOf(userItemFeed())
    }
    private val mockUserDao: UserDao = mock()

    private val systemUnderTest = UserItemRemoteMediator(
        mockUserDao,
        mockGitHubFeedService
    )

    @Test
    fun `returns success with end pagination reached when load type prepend`() = runTest {
        val loadType = LoadType.PREPEND
        val pagingState = PagingState<Int, UserEntity>(
            listOf(),
            null,
            PagingConfig(pageSize = 20),
            0
        )

        val result = systemUnderTest.load(loadType, pagingState)
        assert(result is RemoteMediator.MediatorResult.Success && result.endOfPaginationReached)
    }

    @Test
    fun `fetches users when load type refresh and calls database transaction`() = runTest {
        val perPage = 123
        val loadType = LoadType.REFRESH
        val pagingState = PagingState<Int, UserEntity>(
            listOf(),
            null,
            PagingConfig(pageSize = perPage),
            0
        )

        systemUnderTest.load(loadType, pagingState)

        inOrder(mockGitHubFeedService, mockUserDao) {
            verify(mockGitHubFeedService).getUsers(
                authToken = any(),
                since = eq(0),
                perPage = eq(perPage)
            )
            verify(mockUserDao).tryDeleteAndInsertAll(
                users = any(),
                shouldDelete = eq(true)
            )
        }
    }

    @Test
    fun `fetches users when load type append and calls database transaction`() = runTest {
        val perPage = 123
        val loadType = LoadType.APPEND
        val pagingState = PagingState<Int, UserEntity>(
            listOf(),
            null,
            PagingConfig(pageSize = perPage),
            0
        )

        systemUnderTest.load(loadType, pagingState)

        inOrder(mockGitHubFeedService, mockUserDao) {
            verify(mockGitHubFeedService).getUsers(
                authToken = any(),
                since = eq(0),
                perPage = eq(perPage)
            )
            verify(mockUserDao).tryDeleteAndInsertAll(
                users = any(),
                shouldDelete = eq(false)
            )
        }
    }
}