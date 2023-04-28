package com.example.warrenlogin.feature_login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.warrenlogin.feature_login.domain.use_case.AccessUseCase
import com.example.warrenlogin.feature_login.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    init {
        viewModelScope.launch {
            when(accessUseCase()) {
                is Resource.Success -> {


                }
                is Resource.Error -> {

                }
            }
        }
    }

    private suspend fun getAccess(): Boolean {
      return accessUseCase() is Resource.Success
    }
}