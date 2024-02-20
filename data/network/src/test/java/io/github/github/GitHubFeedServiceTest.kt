package io.github.github

import io.github.network.github.GitHubFeedService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubFeedServiceTest {

    private val mockGitHubFeedService = mock<GitHubFeedService>()
    private val authToken = "test_auth_token"
    private val since = 0
    private val perPage = 30
    private val userName = "testUser"

    @Test
    fun `returns user list when getUsers is called`() = runTest {
        // Given
        val expectedUsers = listOf(userItemFeed())
        whenever(mockGitHubFeedService.getUsers(authToken, since, perPage)).thenReturn(expectedUsers)

        // When
        val users = mockGitHubFeedService.getUsers(authToken, since, perPage)

        // Then
        verify(mockGitHubFeedService).getUsers(authToken, since, perPage)
        assert(users == expectedUsers)
    }

    @Test
    fun `returns user details when getUserDetails is called`() = runTest {
        // Given
        val expectedUserDetails = userDetailsFeed()
        whenever(mockGitHubFeedService.getUserDetails(authToken, userName)).thenReturn(expectedUserDetails)

        // When
        val userDetails = mockGitHubFeedService.getUserDetails(authToken, userName)

        // Then
        verify(mockGitHubFeedService).getUserDetails(authToken, userName)
        assert(userDetails == expectedUserDetails)
    }
}


