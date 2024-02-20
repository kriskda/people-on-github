package io.github.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
)