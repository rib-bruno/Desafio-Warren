package com.example.warrenlogin.feature_user.domain.entities

data class User (
    val id: String,
    val background: Background,
    val goalAmount: Int,
    val goalDate: String,
    val name: String,
    val totalBalance: Double) {



}