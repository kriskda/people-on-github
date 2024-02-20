package io.github.kriskda.user.details.model

data class UserDetails(
    val id: Int,
    val userName: String,
    val avatarUrl: String,
    val profileUrl: String,
    val publicReposCount: String,
    val followersCount: String,
    val followingCount: String,
)