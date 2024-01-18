package com.example.warrenlogin.feature_login.data.response

import com.example.warrenlogin.feature_login.data.database.LoginDb
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.google.gson.annotations.SerializedName

//convertendo instâncias do Login response
data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
) {
    fun toLoginDb(): LoginDb {
        return LoginDb(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun toAccess(): Access {
        return Access(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}