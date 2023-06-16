package com.example.warrenlogin.feature_user.data

import com.example.warrenlogin.feature_user.data.database.UserDb
import com.example.warrenlogin.feature_user.data.response.PortfolioResponse
import com.example.warrenlogin.feature_user.domain.entities.User


fun  List<PortfolioResponse>.toUserDb() = map {
    UserDb(
        id = it._id,
        name = it.name,
        totalBalance = it.totalBalance,
        goalAmount = it.goalAmount,
        goalDate = it.goalDate,
        background = it.background.toBackGroundDb()
    )
}

fun  List<PortfolioResponse>.toUserDomain() = map {
    User(
        name = it.name,
        totalBalance = it.totalBalance,
        goalAmount = it.goalAmount,
        goalDate = it.goalDate,
        id = it._id,
        background = it.background.toBackGroundDomain()
    )
}

fun List<UserDb>.toUser() = map {
    User(
        name = it.name,
        totalBalance = it.totalBalance,
        goalAmount = it.goalAmount,
        goalDate = it.goalDate,
        id = it.id,
        background = it.background.backgroundDbToDomain()
    )
}