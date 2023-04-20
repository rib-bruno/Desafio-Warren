package com.example.warrenlogin.di

import com.example.warrenlogin.feature_login.data.database.LoginDao
import com.example.warrenlogin.feature_login.data.remote.LoginApi
import com.example.warrenlogin.feature_login.data.repository.LoginRepositoryImpl
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.use_case.LoginUseCase
import com.example.warrenlogin.other.Constants.BASE_URL
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
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginApi(client: OkHttpClient) : LoginApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun providesLoginRepository(api: LoginApi, dao: LoginDao) : LoginRepository {
        return LoginRepositoryImpl(api,dao)
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: LoginRepository) : LoginUseCase {
        return LoginUseCase((repository))
    }
}