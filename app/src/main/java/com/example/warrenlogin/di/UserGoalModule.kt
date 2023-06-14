package com.example.warrenlogin.di

import com.example.warrenlogin.feature_login.data.database.LoginDao
import com.example.warrenlogin.feature_login.data.remote.LoginApi
import com.example.warrenlogin.feature_login.data.repository.LoginRepositoryImpl
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.repository.UserGoalsRepositoryImpl
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import com.example.warrenlogin.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object UserGoalModule {

    @Provides
    @Singleton
    fun provideUserApi(client: OkHttpClient) : UserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun providesLoginRepository(api: UserApi, dao: UserDao) : UserGoalsRepository {
        return UserGoalsRepositoryImpl(api, dao)
    }


}