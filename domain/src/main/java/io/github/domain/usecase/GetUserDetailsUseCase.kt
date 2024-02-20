package io.github.domain.usecase

import io.github.domain.user.UserDetailsDomain
import io.github.domain.user.UserRepositoryInterface
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepositoryInterface
) {

    suspend operator fun invoke(userName: String): UserDetailsDomain =
        userRepository.getUserDetails(userName)
}