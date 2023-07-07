package com.example.warrenlogin.di

import android.content.Context
import androidx.room.Room
import com.example.warrenlogin.feature_login.data.database.AppDatabase
import com.example.warrenlogin.feature_user.data.database.UserGoalsDatabase
import com.example.warrenlogin.other.Constants.DATABASE_NAME
import com.example.warrenlogin.other.Constants.DATABASE_USER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesLoginDao(
        database: AppDatabase
    ) = database.loginDao()

    @Singleton
    @Provides
    fun providesUserDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,UserGoalsDatabase::class.java, DATABASE_USER).build()

    @Singleton
    @Provides
    fun providesUserGoalsDao (
        database: UserGoalsDatabase
    ) = database.userDao()
}