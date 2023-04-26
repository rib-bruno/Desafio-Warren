package com.example.warrenlogin.feature_login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.use_case.LoginUseCase
import com.example.warrenlogin.feature_login.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val  _loginState : MutableLiveData<Resource<Access>> = MutableLiveData()
    val loginState: LiveData<Resource<Access>>
    get() = _loginState

    fun doLogin(email: String, password: String) = viewModelScope.launch {
       _loginState.value = loginUseCase.invoke(email, password)
//        val loginResult = loginUseCase.invoke(email, password)

       }


    }


