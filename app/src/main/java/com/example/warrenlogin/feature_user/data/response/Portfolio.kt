package com.example.warrenlogin.feature_user.data.response

import com.google.gson.annotations.SerializedName

data class PortfolioResponse(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("background")
    val background: Background,
    @SerializedName("goalAmount")
    val goalAmount: Int,
    @SerializedName("goalDate")
    val goalDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("totalBalance")
    val totalBalance: Double
)