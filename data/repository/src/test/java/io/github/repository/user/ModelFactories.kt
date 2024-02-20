package io.github.repository.user

import io.github.network.github.model.UserItemFeed

fun userItemFeed(
    id: Int = 1,
    login: String = "testUser",
    avatarUrl: String = "https://example.com/avatar"
) = UserItemFeed(id, login, avatarUrl)