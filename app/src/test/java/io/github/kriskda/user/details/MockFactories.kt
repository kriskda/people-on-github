package io.github.kriskda.user.details

import io.github.domain.user.UserDetailsDomain

fun userDetailsDomain() = UserDetailsDomain(
    id = 1,
    userName = "testUser",
    avatarUrl = "https://avatar.url",
    profileUrl = "https://profile.url",
    publicReposCount = 10,
    followersCount = 100,
    followingCount = 50
)