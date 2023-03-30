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
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        ).allowMainThreadQueries().build()
        dao = database.loginDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertLogin() = runTest {
        val loginItem = LoginDb(id = 1, "bruno", "ribeiro")
        dao.insert(loginItem)

        val allLoginItem = dao.getLogin()

        assertThat(allLoginItem).isEqualTo(loginItem)
    }

    @Test
    fun deleteLogin() = runTest{
        val loginItem = LoginDb(id = 1, "bruno", "ribeiro")
        dao.insert(loginItem)
        dao.deleteAll()

        val allLoginItem = dao.getLogin()

        assertThat(allLoginItem).isNotEqualTo(loginItem)
    }

    @Test
    fun saveLogin() = runTest {
        val loginItem = LoginDb(id = 1, "bruno", "ribeiro")
        dao.saveLogin(loginItem)
        val allLoginItem = dao.getLogin()

        assertThat(allLoginItem).isEqualTo(loginItem)


    }


}