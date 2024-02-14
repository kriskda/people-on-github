package io.github.domain

import io.github.domain.user.User
import javax.inject.Inject

class GetUserDetails @Inject constructor(

) {

    suspend operator fun invoke(userId: String) =
        User(
            id = "User $userId",
            userName = "User $userId",
            avatarUrl = "https://avatars.githubusercontent.com/u/2589087?s=400&u=b921ee47bbc3ef5e1b03e45877d337ea16bc7590&v=4"
        )
}