package io.github.kriskda.user.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.domain.usecase.GetUserDetailsUseCase
import io.github.kriskda.di.MainDispatcher
import io.github.kriskda.user.details.model.UserDetailsNavArgs
import io.github.kriskda.user.details.model.UserDetailsUiState
import io.github.kriskda.user.navArgs
import io.github.kriskda.user.toUserDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    @MainDispatcher coroutineDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle,
    getUserDetailsUseCase: GetUserDetailsUseCase,
) : ViewModel() {

    private val userName = savedStateHandle.navArgs<UserDetailsNavArgs>().userName

    private val _userDetailsUiState: MutableStateFlow<UserDetailsUiState> = MutableStateFlow(UserDetailsUiState.Loading)
    val userDetailsUiState: StateFlow<UserDetailsUiState> = _userDetailsUiState

    init {
        viewModelScope.launch(coroutineDispatcher) {
            runCatching {
                getUserDetailsUseCase(userName)
            }.onSuccess {
                _userDetailsUiState.value = UserDetailsUiState.Loaded(it.toUserDetails())
            }.onFailure {
                _userDetailsUiState.value = UserDetailsUiState.Failure
            }
        }
    }
}