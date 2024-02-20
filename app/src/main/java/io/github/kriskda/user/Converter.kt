package io.github.kriskda.user

import io.github.domain.user.UserDetailsDomain
import io.github.domain.user.UserDomain
import io.github.kriskda.user.details.model.UserDetails
import io.github.kriskda.user.list.model.UserItem

internal fun UserDomain.toUserItem() =
    UserItem(
        id = id,
        userName = userName,
        avatarUrl = avatarUrl,
    )

internal fun UserDetailsDomain.toUserDetails() =
    UserDetails(
        id = id,
        userName = userName,
        avatarUrl = avatarUrl,
        profileUrl = profileUrl,
        publicReposCount = publicReposCount.toString(),
        followersCount = followersCount.toString(),
        followingCount = followingCount.toString(),
    )