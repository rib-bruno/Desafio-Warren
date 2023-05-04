package com.example.warrenlogin.feature_login.presentation

import androidx.lifecycle.*
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.use_case.AccessUseCase
import com.example.warrenlogin.feature_login.domain.use_case.LoginUseCase
import com.example.warrenlogin.feature_login.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
  //  private val accessUseCase: AccessUseCase
) : ViewModel() {

    private val  _loginState : MutableLiveData<Resource<Access>> = MutableLiveData()
    val loginState: LiveData<Resource<Access>>
    get() = _loginState

//    private val _isLoading = MutableLiveData(true)
//    val isLoading :  LiveData<Boolean> = _isLoading


//    val accessState: LiveData<Boolean> = liveData {
//        val result = getAccess()
//
//        delay(2000)
//        emit(result)
//    }

    fun doLogin(email: String, password: String) = viewModelScope.launch {
       _loginState.value = loginUseCase.invoke(email, password)
//        val loginResult = loginUseCase.invoke(email, password)

       }

//     private suspend fun getAccess(): Boolean {
//        //return accessUseCase() is Resource.Success
//        val result = accessUseCase.invoke()
//         _isLoading.value = false
//        return result is Resource.Success
//    }


    }


