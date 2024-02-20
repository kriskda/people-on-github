package io.github.network.github.model

import com.google.gson.annotations.SerializedName

data class UserDetailsFeed(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("starred_url") val starredUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("public_repos") val publicRepos: Int?,
    @SerializedName("followers") val followers: Int?,
    @SerializedName("following") val following: Int?,
)