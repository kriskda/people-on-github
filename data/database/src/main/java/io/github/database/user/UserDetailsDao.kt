package io.github.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetails: UserDetailsEntity)

    @Query("SELECT * FROM userdetailsentity WHERE user_name=:userName")
    suspend fun getUser(userName: String): UserDetailsEntity

    @Query("DELETE FROM userdetailsentity")
    suspend fun deleteAll()
}