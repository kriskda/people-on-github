package io.github.kriskda.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.domain.usecase.GetUsersUseCase
import io.github.kriskda.user.toUserItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val usersPagingFlow = getUsersUseCase()
        .map { pagingData ->
            pagingData.map { it.toUserItem() }
        }.cachedIn(viewModelScope)
}