package com.example.warrenlogin.feature_user.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.warrenlogin.feature_user.data.response.Background
import com.example.warrenlogin.feature_user.domain.entities.User
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserDb(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "total_balance") val totalBalance: Double,
    @ColumnInfo(name = "goal_amount") val goalAmount: Int,
    @ColumnInfo(name = "goal_date") val goalDate: String,

    //marca um campo de um Entity para permitir que campos aninhados (campo de classe do campo anotado) sejam referenciados
    //diretamente nas consultas SQL
    @Embedded val background: BackgroundDb
) {
    fun toUser(): User{
        return User(
            name = name,
            totalBalance = totalBalance,
            goalAmount = goalAmount,
            goalDate = goalDate,
        )
    }
}

data class BackgroundDb(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)