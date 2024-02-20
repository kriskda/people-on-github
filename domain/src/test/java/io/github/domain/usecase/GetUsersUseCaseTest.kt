package io.github.domain.usecase

import androidx.paging.PagingData
import io.github.domain.user.UserDomain
import io.github.domain.user.UserRepositoryInterface
import io.github.domain.userDomains
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetUsersUseCaseTest {

    private val mockUserRepository: UserRepositoryInterface = mock()
    private val systemUnderTest = GetUsersUseCase(mockUserRepository)

    @Test
    fun `invokes get users from repository`() = runTest {
        // Given
        val requestedPageSize = 30
        assumePagedUsers()

        // When
        val result = systemUnderTest().first()

        // Then
        verify(mockUserRepository).getUsers(requestedPageSize)
        assert(result is PagingData<UserDomain>)
    }

    private fun assumePagedUsers() {
        val expectedPagingData: Flow<PagingData<UserDomain>> = flowOf(PagingData.from(userDomains()))
        whenever(mockUserRepository.getUsers(any())).thenReturn(expectedPagingData)
    }
}
