package io.github.domain.usecase

import io.github.domain.user.UserRepositoryInterface
import io.github.domain.userDetailsDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetUserDetailsUseCaseTest {

    private val mockUserRepository: UserRepositoryInterface = mock()
    private val systemUnderTest = GetUserDetailsUseCase(mockUserRepository)

    @Test
    fun `invokes user details from repository`() = runTest {
        // Given
        val userName = "testUser"
        assumeUserExists()

        // When
        val result = systemUnderTest(userName)

        // Then
        verify(mockUserRepository).getUserDetails(userName)
        assert(result == userDetailsDomain())
    }

    private fun assumeUserExists() = runTest {
        whenever(mockUserRepository.getUserDetails(any())).thenReturn(userDetailsDomain())
    }
}