package io.github.kriskda.user.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.domain.GetUserDetails
import io.github.kriskda.user.details.model.UserDetailsNavArgs
import io.github.kriskda.user.details.model.UserDetailsUiState
import io.github.kriskda.user.navArgs
import io.github.kriskda.user.toUserDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getUserDetails: GetUserDetails,
) : ViewModel() {

    private val userId = savedStateHandle.navArgs<UserDetailsNavArgs>().userId

    val userDetailsUiState: StateFlow<UserDetailsUiState> = flow {
        runCatching {
            getUserDetails(userId)
        }.onSuccess {
            emit(UserDetailsUiState.Loaded(it.toUserDetails()))
        }.onFailure {
            emit(UserDetailsUiState.Failure)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UserDetailsUiState.Loading
    )
}