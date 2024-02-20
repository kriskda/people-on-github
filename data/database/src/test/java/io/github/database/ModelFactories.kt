package io.github.database

import io.github.database.user.UserDetailsEntity
import io.github.database.user.UserEntity

fun userDetails() = UserDetailsEntity(
    id = 1,
    userName = "Jane Doe",
    avatarUrl = "https://example.com/avatar2.jpg",
    profileUrl = "https://example.com/profile2",
    publicReposCount = 20,
    followersCount = 200,
    followingCount = 150
)

fun userEntities() = listOf(
    UserEntity(1, "User1", "url1"),
    UserEntity(2, "User2", "url2")
)