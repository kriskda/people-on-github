package io.github.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailsEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "profile_url") val profileUrl: String,
    @ColumnInfo(name = "profile_repos_count") val publicReposCount: Int,
    @ColumnInfo(name = "followers_count") val followersCount: Int,
    @ColumnInfo(name = "following_count") val followingCount: Int,
)