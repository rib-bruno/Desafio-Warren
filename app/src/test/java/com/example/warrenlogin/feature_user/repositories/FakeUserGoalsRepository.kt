package com.example.warrenlogin.feature_user.repositories

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.repository.UserGoalsRepositoryImpl
import com.example.warrenlogin.feature_user.domain.entities.Background
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner



@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi

class FakeUserGoalsRepository {



    @Mock
    lateinit var remoteDateSource: UserApi

    @Mock
    private lateinit var userRepository: UserGoalsRepository

    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userRepository = UserGoalsRepositoryImpl(remoteDateSource, userDao)
    }

    @Test
    fun testUserGoals() = runBlocking {
        val token = "access_token"
        val userList = listOf(User("User 1", goalAmount = 100, goalDate = "user_1", name = "Bruno", totalBalance = 25.25,
            background = Background(full = "imagem 1", raw = "imagem 01", regular = "imagem 01", small = "imagem 01", thumb = "imagem 01")),
        User("User 2", goalAmount = 200, goalDate = "user_2", name = "Joao", totalBalance = 50.50,
            background = Background(full = "imagem 2", raw = "imagem 02", regular = "imagem 002", small = "imagem 0002", thumb = "imagem 00002"))
            )

        Mockito.`when`(remoteDateSource.getUserGoals(token)).thenReturn(
            flowOf(Resource.Success(userList))
        )

    }

}