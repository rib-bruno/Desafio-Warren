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
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    //armazenar o estado de login
    private val _loginState: MutableLiveData<LoginState> = MutableLiveData()
    val loginState: LiveData<LoginState>
        get() = _loginState

    fun doLogin(email: String, password: String) = viewModelScope.launch {
        try {
            //iniciando com o estado de login
            _loginState.value = LoginState.Loading

            //atualizar o estado
            when(val result = loginUseCase(email, password)) {
                is Resource.Success -> {
                    _loginState.value = result.data?.let { LoginState.Success(it) }
                }
                is Resource.Error -> {
                    _loginState.value = LoginState.Error(result.message ?: "Erro Desconhecido!")
                }
                else -> {
                }
            }
        } catch (e: Exception) {
            _loginState.value = LoginState.Error("Erro ao realizar o login: ${e.message}")
        }
    }
}

//TODO 04 - CRIAR UMA FUNÇÃO PARA LIDAR COM OS ERROS ESPECÍFICOS

sealed class LoginState {
    //TODO 05 - CRIAR UMA CLASSE STATE QUE CONTEMPLE O PROJETO TODO
    object Loading : LoginState()
    data class Success(val access: Access) : LoginState()
    data class Error(val message: String) : LoginState()
}

