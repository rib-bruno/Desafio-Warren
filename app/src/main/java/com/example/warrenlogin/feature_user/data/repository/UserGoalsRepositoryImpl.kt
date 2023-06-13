package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.toUser
import com.example.warrenlogin.feature_user.data.toUserDb
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import retrofit2.HttpException
import java.io.IOException

class UserGoalsRepositoryImpl(
    private val userGoalsAPi: UserApi,
    private val userGoalsDao: UserDao
) : UserGoalsRepository {

    override suspend fun getUserGoals(token: String): Resource<List<User>> {
        return try {
            val response = userGoalsAPi.getUserGoals(token)

            if (response.isSuccessful) {
                response.body()?.let {
                    println("salvando os dados de usuários ${it.listIterator()}")
                    userGoalsDao.saveUsers(it.toUserDb()) }

                Resource.Success(
                    userGoalsDao.getAll().toUser()
                )

            } else {
                response.errorBody().let {
                    Resource.Error("Não foi possível salvar os dados na DB")
                }
            }
        } catch (e: IOException) {
            Resource.Error(
                "Oops! Couldn't reach server. Check your internet connection."
            )
        } catch (e: HttpException) {
            Resource.Error (
                " Oops! Something went wrong. Please try again"
                    )
        }
    }
}