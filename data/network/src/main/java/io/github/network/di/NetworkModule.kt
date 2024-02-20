package io.github.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.network.github.GitHubFeedService
import io.github.network.github.GithubConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGithubFeedService(): GitHubFeedService =
        Retrofit.Builder()
            .baseUrl(GithubConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubFeedService::class.java)
}
