package com.example.warrenlogin.di

import android.content.Context
import androidx.room.Room
import com.example.warrenlogin.feature_login.data.database.AppDatabase
import com.example.warrenlogin.other.Constants.DATABASE_NAME
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
}