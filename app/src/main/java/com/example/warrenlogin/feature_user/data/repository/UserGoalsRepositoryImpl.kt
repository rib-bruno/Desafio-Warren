package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.toUser
import com.example.warrenlogin.feature_user.data.toUserDb
import com.example.warrenlogin.feature_user.data.toUserDomain
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserGoalsRepositoryImpl(
    private val userGoalsAPi: UserApi,
    private val userGoalsDao: UserDao
) : UserGoalsRepository {

    override suspend fun getUserGoals(token: String): Flow<Resource<List<User>>>  = flow {

        try {
            userGoalsAPi.getUserGoals(token).let {
                if (it.isSuccessful) {
                    val response = it.body()!!.toUserDomain()
                    saveUserGoals(response)
                    emit(Resource.Success(response))
                }
            }
            //saveUserGoals(userGoalsList)
           // emit(Resource.Success(userGoalsList))
        } catch (e: Exception) {
            if (listUser().data?.isNotEmpty() == true){
                emit(listUser())
            } else {
                emit(Resource.Error("não foi possível acessar os dados  da databse -> $e"))
            }

        }


//        return try {
//            val response = userGoalsAPi.getUserGoals(token)
//            if (response.isSuccessful) {
//
//                //evitando chamar response.body várias vezes
//                val users = response.body()?.toUserDomain()
//                users?.let {
//                    userGoalsDao.saveUsers(it.toUserDb())
//                }
//                Resource.Success(users)
//            } else {
//                getUserGoalsLocal()
//            }
//        } catch (e: IOException) {
//            Resource.Error(
//                "Oops! Couldn't reach server. Check your internet connection."
//            )
//        } catch (e: HttpException) {
//            Resource.Error(
//                " Oops! Something went wrong. Please try again"
//            )
//        }

    }

    override suspend fun listUser(): Resource<List<User>> {
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



    override suspend fun saveUserGoals(user: List<User>) {
        userGoalsDao.saveUsers(user.toUserDb())
    }

//    private fun getUserGoalsLocal(): Resource<List<User>> {
//        return try {
//            val localUsers = userGoalsDao.getAll()
//            if (localUsers.isEmpty()) {
//               Resource.Error("Não há dados locais disponíveis!")
//            } else {
//                Resource.Success(localUsers.toUser())
//            }
//        } catch (e: Exception) {
//            Resource.Error("Erro inesperado!")
//        }
//    }


}

//class UserGoalsRepositoryImpl(
//    private val userGoalsApi: UserApi,
//    private val userGoalsDao: UserDao
//) : UserGoalsRepository {
//
//    override suspend fun getUserGoals(token: String): Resource<List<User>> {
//        return try {
//            val response = userGoalsApi.getUserGoals(token)
//            if (response.isSuccessful) {
//                val users = response.body()?.toUserDomain()
//                users?.let {
//                    saveUsersToLocalDatabase(it)
//                }
//                Resource.Success(users)
//            } else {
//                getUserGoalsLocal()
//            }
//        } catch (e: IOException) {
//            Resource.Error("Oops! Couldn't reach the server. Check your internet connection.")
//        } catch (e: HttpException) {
//            Resource.Error("Oops! Something went wrong. Please try again.")
//        }
//    }
//
//    private fun saveUsersToLocalDatabase(users: List<User>) {
//        userGoalsDao.saveUsers(users.toUserDb())
//    }
//
//    private fun getUserGoalsLocal(): Resource<List<User>> {
//        return try {
//            val localUsers = userGoalsDao.getAll()
//            if (localUsers.isEmpty()) {
//                Resource.Error("Não há dados locais disponíveis!")
//            } else {
//                Resource.Success(localUsers.toUser())
//            }
//        } catch (e: Exception) {
//            Resource.Error("Erro inesperado!")
//        }
//    }
//}