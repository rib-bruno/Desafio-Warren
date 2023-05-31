package com.example.warrenlogin.feature_user.data.response

import com.example.warrenlogin.feature_user.data.database.BackgroundDb
import com.example.warrenlogin.feature_user.data.database.UserDb
import com.example.warrenlogin.feature_user.domain.entities.User
import com.google.gson.annotations.SerializedName

data class PortfolioResponse(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("background")
    val background: BackgroundResponse,
    @SerializedName("goalAmount")
    val goalAmount: Int,
    @SerializedName("goalDate")
    val goalDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("totalBalance")
    val totalBalance: Double
) {
    fun toUserDb() : UserDb {
        return UserDb(
            id = _id,
            name = name,
            totalBalance = totalBalance,
            goalAmount = goalAmount,
            goalDate = goalDate,
            background = background.toBackGroundDb()

        )
    }

//    fun toUser() : User {
//        return User(
//            id = _id,
//            name = name,
//            totalBalance = totalBalance,
//            goalAmount = goalAmount,
//            goalDate = goalDate,
//            background = background.()
//        )
//    }
}


