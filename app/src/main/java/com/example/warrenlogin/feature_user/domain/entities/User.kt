package com.example.warrenlogin.feature_user.domain.entities

import com.example.warrenlogin.feature_user.data.response.Background
import com.google.gson.annotations.SerializedName

data class User (
    val id: String,
    val background: Background,
    val goalAmount: Int,
    val goalDate: String,
    val name: String,
    val totalBalance: Double) {
}