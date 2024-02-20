package io.github.domain.usecase

import androidx.paging.PagingData
import io.github.domain.user.UserDomain
import io.github.domain.user.UserRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepositoryInterface
) {

    companion object {
        private const val PAGE_SIZE = 30
    }

    operator fun invoke(): Flow<PagingData<UserDomain>> =
        userRepository.getUsers(pageSize = PAGE_SIZE)
}