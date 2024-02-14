package io.github.kriskda.user

import io.github.domain.user.User
import io.github.kriskda.user.details.model.UserDetails
import io.github.kriskda.user.list.model.UserItem

internal fun List<User>.toUserList() =
    map {
        UserItem(
            id = it.id,
            userName = it.userName,
            avatarUrl = it.avatarUrl,
        )
    }

internal fun User.toUserDetails() =
    UserDetails(
        id = id,
        userName = userName,
        avatarUrl = avatarUrl,
    )