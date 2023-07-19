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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUserUseCase: GetUsersUseCase,
    private val getAccessUseCase: AccessUseCase,
) : ViewModel() {

    private val _userGoalsLiveData = MutableLiveData<ViewState<List<User>>>()
    val userGoalsLiveData: LiveData<ViewState<List<User>>>
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
           // _userGoalsLiveData.value = Resource.Loading
            try {
                val resource = getUserGoals(token)
                _userGoalsLiveData.postValue(resource)
            } catch (e: IOException) {
                _userGoalsLiveData.postValue(Resource.Error("Oops! Não foi possível conectar ao servidor. Verifique sua conexão com a internet."))
            } catch (e: Exception) {
                _userGoalsLiveData.postValue(Resource.Error("Oops! Algo deu errado. Por favor, tente novamente."))
            }

//            val resource = getUserGoals(token)
//            when(resource) {
//                is Resource.Success -> {
//                    _userGoalsLiveData.value = resource
//                }
//                is Resource.Error -> {
//                    _userGoalsLiveData.value =
//                }
//            }
        }
    }

    private suspend fun emitGoalsState(state: ViewState<List<User>>) {
        withContext(Dispatchers.Main) {
            userGoalsLiveData.value = state
        }

    }

    //recuperando o token de acesso
    private suspend fun getAccess(): Resource<Access> {

        val result = getAccessUseCase()

        return if (result is Resource.Success) {
            Resource.Success(result.data)
        } else {
            Resource.Error("Não foi possível localizar o token de acesso.")
        }
    }

    //chamando o use case
    private suspend fun getUserGoals(token: String) : Flow<Resource<List<User>>> {
        return getUserUseCase(token)
    }

}



