package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.toUser
import com.example.warrenlogin.feature_user.data.toUserDb
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

class UserGoalsRepositoryImpl(
    private val userGoalsAPi: UserApi,
    private val userGoalsDao: UserDao
) : UserGoalsRepository {

    override suspend fun getUserGoals(token: String): Resource<List<User>> {
        try {
           val response = userGoalsAPi.getUserGoals(token)
            Resource.Success(
                data = userGoalsDao.saveUsers(response.toUserDb())
            )
        } catch (e: IOException) {
            Resource.Error(
                "oops, couldn't reach server"
            )
        }

    return
    }
}