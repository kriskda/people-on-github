package io.github.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.database.AppDatabase
import io.github.database.user.UserDao

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDatabaseModule {

    companion object {

        @JvmStatic
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "app-database"
            ).build()

        @JvmStatic
        @Provides
        fun provideUserDao(database: AppDatabase): UserDao =
            database.userDao()

    }
}