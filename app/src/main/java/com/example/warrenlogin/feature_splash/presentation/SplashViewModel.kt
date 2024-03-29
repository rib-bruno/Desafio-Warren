package com.example.warrenlogin.feature_splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.warrenlogin.feature_login.domain.use_case.AccessUseCase
import com.example.warrenlogin.feature_login.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accessUseCase: AccessUseCase
) : ViewModel() {

 val accessState: LiveData<Boolean> = liveData {
    val result = getAccess()
    delay(2000)
    emit(result)
}

    private suspend fun getAccess(): Boolean {
      //return accessUseCase() is Resource.Success
        val result = accessUseCase()
        return result is Resource.Success
    }
}