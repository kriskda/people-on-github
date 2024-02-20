package io.github.network.github

import io.github.network.github.model.UserDetailsFeed
import io.github.network.github.model.UserItemFeed
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

// TODO Provide proper github auth token
private const val AUTH_TOKEN = ""

interface GitHubFeedService {

    @GET("users")
    suspend fun getUsers(
        @Header("Authorization") authToken: String = AUTH_TOKEN,
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): List<UserItemFeed>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Header("Authorization") authToken: String = AUTH_TOKEN,
        @Path("username") userName: String
    ): UserDetailsFeed
}