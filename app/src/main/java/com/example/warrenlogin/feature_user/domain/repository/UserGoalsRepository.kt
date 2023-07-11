package com.example.warrenlogin.feature_user.domain.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.domain.entities.User
import kotlinx.coroutines.flow.Flow


interface UserGoalsRepository {
    suspend fun getUserGoals(token: String) : Flow<Resource<List<User>>>

    suspend fun listUser(): Resource<List<User>>

    suspend fun saveUserGoals(user: List<User>)

}