package io.github.database.user

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM userentity Order By id")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun deleteAll()

    @Transaction
    suspend fun tryDeleteAndInsertAll(users: List<UserEntity>, shouldDelete: Boolean) {
        if (shouldDelete) {
            deleteAll()
        }
        insertAll(users)
    }
}