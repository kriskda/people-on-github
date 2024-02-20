package io.github.kriskda.user.details

import androidx.lifecycle.SavedStateHandle
import io.github.domain.usecase.GetUserDetailsUseCase
import io.github.kriskda.user.details.model.UserDetailsUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    private val userName = "testUser"
    private val savedStateHandle = SavedStateHandle(mapOf("userName" to userName))
    private val mockGetUserDetailsUseCase: GetUserDetailsUseCase = mock()

    @Test
    fun `emits Loaded state with user details on success`() = runTest {
        // Given
        assumeGetContentSuccess()
        val systemUnderTest = UserDetailsViewModel(
            coroutineDispatcher = UnconfinedTestDispatcher(),
            savedStateHandle = savedStateHandle,
            getUserDetailsUseCase = mockGetUserDetailsUseCase
        )

        // When
        val result = systemUnderTest.userDetailsUiState.first()

        // Then
        assertTrue(result is UserDetailsUiState.Loaded)
        val user = (result as UserDetailsUiState.Loaded).user
        assertEquals(user.id, 1)
        assertEquals(user.userName, userName)
    }

    @Test
    fun `emits Failure state on use case failure`() = runTest {
        // Given
        assumeGetContentFailure()
        val systemUnderTest = UserDetailsViewModel(
            coroutineDispatcher = UnconfinedTestDispatcher(),
            savedStateHandle = savedStateHandle,
            getUserDetailsUseCase = mockGetUserDetailsUseCase
        )

        // When
        val result = systemUnderTest.userDetailsUiState.first()

        // Then
        assertTrue(result is UserDetailsUiState.Failure)
    }

    private fun assumeGetContentSuccess() = runTest {
        whenever(mockGetUserDetailsUseCase.invoke(userName)).thenReturn(userDetailsDomain())
    }

    private fun assumeGetContentFailure() = runTest {
        whenever(mockGetUserDetailsUseCase.invoke(userName)).thenThrow(RuntimeException("An error occurred"))
    }
}