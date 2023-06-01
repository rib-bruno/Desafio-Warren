package com.example.warrenlogin.feature_user.data.repository

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserGoalsRepositoryImpl(
    private val userGoalsAPi: UserApi,
    private val userGoalsDao: UserDao
) : UserGoalsRepository {

    override suspend fun getUserGoals(token: String): Flow<Resource<List<User>>> {
        val getUsersRemote = flow {
            try {
                userGoalsAPi.getUserGoals(token).han
            }
        }
    }
}