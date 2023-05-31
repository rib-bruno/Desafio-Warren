package com.example.warrenlogin.feature_user.domain.entities

import com.google.gson.annotations.SerializedName

data class Background(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)
