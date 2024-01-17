package com.example.warrenlogin.di

import com.example.warrenlogin.feature_user.data.database.UserDao
import com.example.warrenlogin.feature_user.data.remote.UserApi
import com.example.warrenlogin.feature_user.data.repository.UserGoalsRepositoryImpl
import com.example.warrenlogin.feature_user.domain.repository.UserGoalsRepository
import com.example.warrenlogin.feature_user.domain.use_case.GetUsersUseCase
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
    fun providesUserGoalsRepository(api: UserApi, dao: UserDao) : UserGoalsRepository {
        return UserGoalsRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun providesUserGoalsUseCase(repository: UserGoalsRepository) : GetUsersUseCase {
        return GetUsersUseCase(repository)
    }


}