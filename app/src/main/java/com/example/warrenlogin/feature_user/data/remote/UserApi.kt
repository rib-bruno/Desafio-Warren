package com.example.warrenlogin.feature_user.data.remote

import com.example.warrenlogin.feature_user.data.response.ListUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("portfolios/mine")
    suspend fun getUserGoals(@Header("access-token") token: String) : Response<ListUserResponse>
}