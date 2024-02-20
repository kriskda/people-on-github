package io.github.repository

import androidx.paging.PagingData
import androidx.paging.map
import io.github.database.user.UserDetailsEntity
import io.github.database.user.UserEntity
import io.github.domain.user.UserDetailsDomain
import io.github.domain.user.UserDomain
import io.github.network.github.model.UserDetailsFeed
import io.github.network.github.model.UserItemFeed

internal fun List<UserItemFeed>.toUserEntityList() =
    map {
        UserEntity(
            id = it.id,
            userName = it.login ?: "",
            avatarUrl = it.avatarUrl ?: "",
        )
    }

internal fun PagingData<UserEntity>.toUserDomainPagingData() =
    map {
        UserDomain(
            id = it.id,
            userName = it.userName,
            avatarUrl = it.avatarUrl
        )
    }

internal fun UserDetailsEntity.toUserDetails() =
    UserDetailsDomain(
        id = id,
        userName = userName,
        avatarUrl = avatarUrl,
        profileUrl = profileUrl,
        publicReposCount = publicReposCount,
        followersCount = followersCount,
        followingCount = followingCount,
    )

internal fun UserDetailsFeed.toUserDetailsEntity() =
    UserDetailsEntity(
        id = id,
        userName = login ?: "",
        avatarUrl = avatarUrl ?: "",
        profileUrl = htmlUrl ?: "",
        publicReposCount = publicRepos ?: 0,
        followersCount = followers ?: 0,
        followingCount = following ?: 0,
    )