package io.github.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.database.user.UserDao
import io.github.database.user.UserDetailsDao
import io.github.database.user.UserDetailsEntity
import io.github.database.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        UserDetailsEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): UserDetailsDao
}