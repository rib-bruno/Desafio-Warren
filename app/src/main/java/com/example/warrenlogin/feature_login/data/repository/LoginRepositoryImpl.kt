package com.example.warrenlogin.feature_login.data.repository

import com.example.warrenlogin.feature_login.data.remote.LoginApi
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.util.Resource

class LoginRepositoryImpl(
    private val loginApi: LoginApi,

) : LoginRepository {
    override suspend fun doLogin(email: String, password: String): Resource<Access> {
        TODO("Not yet implemented")
    }

    override suspend fun haveAccess(): Resource<Access> {
        TODO("Not yet implemented")
    }
}