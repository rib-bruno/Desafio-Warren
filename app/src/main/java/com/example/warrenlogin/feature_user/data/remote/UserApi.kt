package com.example.warrenlogin.feature_user.data.remote

import com.example.warrenlogin.feature_user.data.response.PortfolioResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("portfolios/mine")
//    suspend fun getUserGoals(@Header("access-token") token: String) : Response<ListUserResponse>
 // suspend fun getUserGoals(@Header("access-token") token: String) : Response<List<PortfolioResponse>>
    suspend fun getUserGoals(@Header("access-token") token: String) : List<PortfolioResponse>
}