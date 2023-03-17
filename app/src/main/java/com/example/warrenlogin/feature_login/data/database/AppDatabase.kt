package com.example.warrenlogin.feature_login.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginDb::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun loginDao() : LoginDao
}