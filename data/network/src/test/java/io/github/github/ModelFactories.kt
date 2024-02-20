package io.github.github

import io.github.network.github.model.UserDetailsFeed
import io.github.network.github.model.UserItemFeed

fun userItemFeed(
    id: Int = 1,
    login: String = "testUser",
    avatarUrl: String = "https://example.com/avatar"
) = UserItemFeed(id, login, avatarUrl)

fun userDetailsFeed(
    id: Int = 1,
    login: String = "testUser",
    avatarUrl: String = "https://example.com/avatar",
    htmlUrl: String = "https://example.com/htmlUrl",
    starredUrl: String = "https://example.com/starredUrl",
    reposUrl: String = "https://example.com/reposUrl",
    email: String = "test@example.com",
    publicRepos: Int = 10,
    followers: Int = 100,
    following: Int = 50
) = UserDetailsFeed(
    id, login, avatarUrl, htmlUrl, starredUrl, reposUrl, email, publicRepos, followers, following
)