package com.example.warrenlogin.data.databse

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.warrenlogin.data.MainCoroutineRule
import com.example.warrenlogin.feature_login.data.database.AppDatabase
import com.example.warrenlogin.feature_login.data.database.LoginDao
import com.example.warrenlogin.feature_login.data.database.LoginDb
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LoginDaoTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var database: AppDatabase
    private lateinit var dao: LoginDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.loginDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    private var loginItem = LoginDb(id = 1, "bruno", "ribeiro")

    private suspend fun addOneItemToDb() {
        dao.insert(loginItem)
    }


    @Test
    fun daoInsert_insertLogin() = runBlocking {
        addOneItemToDb()

        val allLoginItems = dao.getLogin()
        assertEquals(allLoginItems, loginItem)
        //assertThat(allLoginItems).isEqualTo(loginItem)
    }

    @Test
    fun daoDeleteAll_deleteLogin() = runTest {
        addOneItemToDb()
        dao.deleteAll()

        //entraindo ntity do bando de dados
        val allLoginItem = dao.getLogin()

        //assertEquals(allLoginItem, loginItem)
        // assertThat(allLoginItem).isNull()
        assertThat(allLoginItem).isNotEqualTo(loginItem)


    }

//    @Test
//    fun daoGetLogin_getLoginFromDb() = runTest {
//        addOneItemToDb()
//
//        //entraindo entity do bando de dados
//        val allLoginItem = dao.getLogin()
//        assertEquals(allLoginItem, loginItem)
//
//    }

    @Test
    fun daoSaveLogin_returnLoginDb() = runTest {
        addOneItemToDb()
        dao.saveLogin(loginItem)

        val allLoginItem = dao.getLogin()

       // assertThat(allLoginItem).isEqualTo(loginItem)
        assertEquals(allLoginItem, loginItem)


    }


}