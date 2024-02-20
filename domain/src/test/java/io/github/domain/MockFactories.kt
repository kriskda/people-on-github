package io.github.domain

import io.github.domain.user.UserDetailsDomain
import io.github.domain.user.UserDomain

fun userDetailsDomain() =
    UserDetailsDomain(
        id = 123,
        userName = "user name",
        avatarUrl = "https://example.com/avatar",
        profileUrl = "https://example.com/profile",
        publicReposCount = 10,
        followersCount = 3,
        followingCount = 9,
    )

fun userDomains() =
    listOf(
        UserDomain(
            id = 1,
            userName = "user name",
            avatarUrl = "https://example.com/avatar",
        )
    )