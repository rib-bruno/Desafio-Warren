package com.example.warrenlogin.feature_user.domain.use_case

import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository

class GetUsersUseCase (
    private val repository: UserGoalsRepository
        ) {

    suspend operator fun invoke (token: String) : Resource<List<User>> {
        return repository.getUserGoals(token)
    }
}