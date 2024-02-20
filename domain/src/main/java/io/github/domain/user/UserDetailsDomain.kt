package io.github.domain.user

data class UserDetailsDomain(
    val id: Int,
    val userName: String,
    val avatarUrl: String,
    val profileUrl: String,
    val publicReposCount: Int,
    val followersCount: Int,
    val followingCount: Int,
)