package com.example.warrenlogin.feature_login.domain.repository

import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.util.Resource

interface LoginRepository {

    suspend fun doLogin(email: String, password: String) : Resource<Access>
    suspend fun haveAccess() : Resource<Access>
}