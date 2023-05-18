package com.example.warrenlogin.feature_user.data.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @SerializedName("portfolios")
    val portfolios: List<Portfolio>
)