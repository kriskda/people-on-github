package io.github.domain.user

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {

    fun getUsers(pageSize: Int): Flow<PagingData<UserDomain>>

    suspend fun getUserDetails(userName: String) : UserDetailsDomain
}