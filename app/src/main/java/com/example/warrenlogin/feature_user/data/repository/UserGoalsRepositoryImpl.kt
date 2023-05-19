package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import kotlinx.coroutines.flow.Flow

class UserGoalsRepositoryImpl (
    private val userGoalsAPi: UserApi,
    private val userGoalsDao:
        ) : UserGoalsRepository {

    override suspend fun getUserGoals(): Flow<Resource<List<User>>> {
        TODO("Not yet implemented")
    }
}