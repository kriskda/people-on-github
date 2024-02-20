package io.github.database

import androidx.paging.PagingSource
import androidx.room.Room
import io.github.database.user.UserDao
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class UserDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.getApplication(),
            AppDatabase::class.java
        ).build()
        userDao = appDatabase.userDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun `inserts all data`() = runTest {
        // Given
        val users = userEntities()

        // When
        userDao.insertAll(users)

        // Then
        val usersPage = userDao.getUsers().load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertEquals((usersPage as PagingSource.LoadResult.Page).data, users)
    }

    @Test
    fun `deletes all data`() = runTest {
        // Given
        val users = userEntities()
        userDao.insertAll(users)

        // When
        userDao.deleteAll()

        // Then
        val usersPage = userDao.getUsers().load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertEquals((usersPage as PagingSource.LoadResult.Page).data.size, 0)
    }
}
