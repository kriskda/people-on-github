package io.github.kriskda.user.details.model

sealed interface UserDetailsUiState {
    data object Loading: UserDetailsUiState
    data class Loaded(val user: UserDetails): UserDetailsUiState
    data object Failure: UserDetailsUiState
}