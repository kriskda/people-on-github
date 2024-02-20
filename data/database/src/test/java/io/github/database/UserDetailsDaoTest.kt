package io.github.database

import androidx.room.Room
import io.github.database.user.UserDetailsDao
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class UserDetailsDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDetailsDao: UserDetailsDao

    @Before
    fun createDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.getApplication(),
            AppDatabase::class.java
        ).build()
        userDetailsDao = appDatabase.userDetailsDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun `inserts user details and retrieves`() = runTest {
        // Given
        val userDetails = userDetails()

        // When
        userDetailsDao.insert(userDetails)

        // Then
        assertEquals(userDetailsDao.getUser(userDetails.userName), userDetails)
    }

    @Test
    fun `deletes all user details and checks`() = runTest {
        // Given
        val userDetails = userDetails()
        userDetailsDao.insert(userDetails)

        // When
        userDetailsDao.deleteAll()

        // Then
        assertEquals(userDetailsDao.getUser(userDetails.userName), null)
    }
}
