package com.example.warrenlogin.feature_user.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [UserDb::class],
    version = 1
)
abstract class UserGoalsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}