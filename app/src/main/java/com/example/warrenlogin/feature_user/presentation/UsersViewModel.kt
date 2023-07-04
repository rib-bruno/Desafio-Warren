package com.example.warrenlogin.feature_user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.use_case.AccessUseCase
import com.example.warrenlogin.feature_login.domain.util.Resource
import com.example.warrenlogin.feature_user.domain.entities.User
import com.example.warrenlogin.feature_user.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUserUseCase: GetUsersUseCase,
    private val getAccessUseCase: AccessUseCase,
) : ViewModel() {

    private val _userGoalsLiveData: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val userGoalsLiveData: LiveData<Resource<List<User>>>
    get() = _userGoalsLiveData


    init {
       fetchUserGoalsFromDataBase()
    }


    private fun fetchUserGoalsFromDataBase() {
        viewModelScope.launch {
            try {
                val accessResource = getAccess()
                if (accessResource is Resource.Success) {
                    val token = accessResource.data!!.accessToken
                    fetchUserGoals(token)
                } else {
                    _userGoalsLiveData.postValue(Resource.Error ("Não foi possível localizar o token de acesso."))
                }
            } catch (e:Exception) {
                _userGoalsLiveData.postValue(Resource.Error("Oops! Algo deu errado. Por favor, tente novamente."))
            }
        }
    }

    private fun fetchUserGoals(token: String) {
        viewModelScope.launch {
            _userGoalsLiveData.value = Resource.Loading
            try {
                val resource = getUserGoals(token)
                _userGoalsLiveData.postValue(resource)
            } catch (e: IOException) {
                _userGoalsLiveData.postValue(Resource.Error("Oops! Não foi possível conectar ao servidor. Verifique sua conexão com a internet."))
            } catch (e: Exception) {
                _userGoalsLiveData.postValue(Resource.Error("Oops! Algo deu errado. Por favor, tente novamente."))
            }
        }
    }

    //recuperando o token de acesso
    private suspend fun getAccess(): Resource<Access> {
        val result = getAccessUseCase.invoke()
        return if (result is Resource.Success) {
            Resource.Success(result.data)
        } else {
            Resource.Error("Não foi possível localizar o token de acesso.")
        }
    }

    //chamando o use case
    private suspend fun getUserGoals(token: String) : Resource<List<User>> {
        return getUserUseCase.invoke(token)
    }

}



