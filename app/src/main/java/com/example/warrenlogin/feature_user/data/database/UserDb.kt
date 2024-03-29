package com.example.warrenlogin.feature_user.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.warrenlogin.feature_user.domain.entities.Background
import com.example.warrenlogin.feature_user.domain.entities.User

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
//    fun List<UserDb>. toUser() = map {
//        User(
//            name = name,
//            totalBalance = totalBalance,
//            goalAmount = goalAmount,
//            goalDate = goalDate,
//            id = id,
//            background = background.backgroundDbToDomain()
//        )
//    }

}

data class BackgroundDb(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
) {
    fun backgroundDbToDomain(): Background {
        return Background(
            full = full,
            raw = raw,
            regular = regular,
            small = small,
            thumb = thumb
        )
    }
}