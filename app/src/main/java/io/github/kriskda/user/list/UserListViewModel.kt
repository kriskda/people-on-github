package io.github.kriskda.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.domain.GetUsers
import io.github.kriskda.user.list.model.UserItem
import io.github.kriskda.user.toUserList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    getUsers: GetUsers
) : ViewModel() {

    val usersStateFlow: StateFlow<List<UserItem>> = flow<List<UserItem>> {
        runCatching {
            getUsers()
        }.onSuccess {
            emit(it.toUserList())
        }.onFailure {
            emit(emptyList())
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}