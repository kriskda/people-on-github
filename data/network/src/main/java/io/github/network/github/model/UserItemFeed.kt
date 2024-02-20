package io.github.network.github.model

import com.google.gson.annotations.SerializedName

data class UserItemFeed(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
)