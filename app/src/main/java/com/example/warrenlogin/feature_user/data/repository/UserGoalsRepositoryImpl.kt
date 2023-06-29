package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.toUser
import com.example.warrenlogin.feature_user.data.toUserDb2
import com.example.warrenlogin.feature_user.data.toUserDomain
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

                //evitando chamar response.body várias vezes
                val users = response.body()?.toUserDomain()
                users?.let {
                    userGoalsDao.saveUsers(it.toUserDb2())
                }
                Resource.Success(users)
            } else {
                getUserGoalsLocal()
            }
        } catch (e: IOException) {
            Resource.Error(
                "Oops! Couldn't reach server. Check your internet connection."
            )
        } catch (e: HttpException) {
            Resource.Error(
                " Oops! Something went wrong. Please try again"
            )
        }

    }

    private fun getUserGoalsLocal(): Resource<List<User>> {
        return try {
            val localUsers = userGoalsDao.getAll()
            if (localUsers.isEmpty()) {
               Resource.Error("Não há dados locais disponíveis!")
            } else {
                Resource.Success(localUsers.toUser())
            }
        } catch (e: Exception) {
            Resource.Error("Erro inesperado!")
        }
    }

}
