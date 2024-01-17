package com.example.warrenlogin.feature_login.data.repository

import android.util.Log
import com.example.warrenlogin.feature_login.data.database.LoginDao
import com.example.warrenlogin.feature_login.data.remote.LoginApi
import com.example.warrenlogin.feature_login.data.remote.LoginBody
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.util.Resource
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val loginDao: LoginDao,
    ) : LoginRepository {
    override suspend fun doLogin(email: String, password: String): Resource<Access> {
        val request = LoginBody(email, password)

        return try {
            val response = loginApi.doLogin(request)

            if (response.isSuccessful) {
                val responseBody = response.body()

                //salvando na db
                responseBody?.let {
                    Log.d("LoginRepository","Overriding token with ${it.accessToken}")
                    loginDao.saveLogin(it.toLoginDb())
                    Resource.Success(it.toAccess())
                } ?: Resource.Error("Resposta vazia do servidor!")

            } else {
              Resource.Error("Erro ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            Resource.Error("Erro de conexão: ${e.message}!")
        } catch (e: Exception) {
            Resource.Error("Algo deu errado: ${e.message}!")
        }
    }

    override suspend fun haveAccess(): Resource<Access> {
        return try {
            val response = loginDao.getLogin()
            Resource.Success(response.toAccess())
        } catch (e: Exception) {
            Resource.Error("Algo deu errado: ${e.message}!")
        }
    }
}