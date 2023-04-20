package com.example.warrenlogin.feature_login.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.warrenlogin.feature_login.domain.entities.Access

@Entity(tableName = "access")
data class LoginDb(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "access_token") val accessToken: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String

)  {
    fun toAccess(): Access {
        return Access(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}

