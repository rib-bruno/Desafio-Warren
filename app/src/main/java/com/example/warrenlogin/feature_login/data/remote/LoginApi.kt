package com.example.warrenlogin.feature_login.data.remote

import com.example.warrenlogin.feature_login.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("account/login")
    suspend fun doLogin(@Body body: LoginBody) : Response <LoginResponse>
}