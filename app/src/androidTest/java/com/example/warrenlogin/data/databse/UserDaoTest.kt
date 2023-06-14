package com.example.warrenlogin.data.databse

import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.warrenlogin.data.MainCoroutineRule
import com.example.warrenlogin.feature_login.data.database.AppDatabase
import com.example.warrenlogin.feature_user.data.database.BackgroundDb
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.database.UserDb
import com.example.warrenlogin.feature_user.data.database.UserGoalsDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest

class UserDaoTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var database: UserGoalsDatabase
    private lateinit var dao: UserDao
    private lateinit var dbUserItem: List<UserDb>

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserGoalsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.userDao()


    }

   @Before
   fun createUserGoalForTest () {

       val backgroundItem = BackgroundDb(full = "teste1", raw = "string2", regular = "string3", small = "string4", thumb = "string5")
       var backgroundItem2 = BackgroundDb(full = "pagina1", raw = "pagina2", regular = "pagina3", small = "pagina4", thumb = "pagina5")

       val userItem = UserDb(id = "1", name = " Bruno", totalBalance = 2.2, goalAmount = 10, goalDate = "paris", background = backgroundItem)
       val userItem2 = UserDb(id = "15", name = " Joao", totalBalance = 5.6, goalAmount = 85, goalDate = "rio doce", background = backgroundItem)

       dbUserItem = listOf(userItem)

   }

    @After
    fun teardown() {
        database.close()
    }

    private suspend fun addOneItemToDb() {
        dao.insertAll(dbUserItem)
    }

    @Test
    fun daoInsert_insertUserItem() = runBlocking {
        addOneItemToDb()
        val allUsersItems = dao.getAll()
        assertEquals(allUsersItems,dbUserItem )
    }





}