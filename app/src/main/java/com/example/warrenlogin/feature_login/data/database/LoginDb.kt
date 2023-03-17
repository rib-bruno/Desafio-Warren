package com.example.warrenlogin.feature_login.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class LoginDb(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "access_token") val accessToken: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String

)
