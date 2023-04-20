package com.example.warrenlogin.feature_login.data.repository

import com.example.warrenlogin.feature_login.data.database.LoginDao
import com.example.warrenlogin.feature_login.data.database.LoginDb
import com.example.warrenlogin.feature_login.data.remote.LoginApi
import com.example.warrenlogin.feature_login.data.remote.LoginBody
import com.example.warrenlogin.feature_login.data.response.LoginResponse
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.util.Resource
import java.io.IOException

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val loginDao: LoginDao,

) : LoginRepository {
    override suspend fun doLogin(email: String, password: String): Resource<Access> {
       val request = LoginBody(email, password)
        return try {
            val response = loginApi.doLogin(request)
            if (response.successful) {
                response.data?.let {
                    println("Overriding token with ${it.accessToken}")
                    loginDao.saveLogin(response.data.toLoginDb())
                }
                Resource.Success(response.data?.toAccess())
            } else {
                response.message?.let {
                    Resource.Error(it)
                } ?: Resource.Error("erro desconhecido")
            }
        } catch (e:IOException) {
            Resource.Error(
                "checar a internet"
            )
        }

    }

    override suspend fun haveAccess(): Resource<Access> {
        return try {
            val response = loginDao.getLogin()
            Resource.Success(response.toAccess())
        } catch (e:Exception) {
            Resource.Error("algum erro ao acessar a DB")
        }
    }
}