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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
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


    //1) Atualizar o método getUserGoalsList() para obter o token de acesso e depois chamar o método getUserGoals()
    // para buscar a lista de usuários.
    //2)Coletar essa lista no método anterior e emitir o estado adequado na LiveData
    //3)implementar um bloco try-catch(por exemplo) para lidar com as execeções durante a chamada da função getUserGoals()
    //4)

    fun getUserGoalsList() {
        viewModelScope.launch(Dispatchers.IO) {
            emitGoalsState(ViewState.Loading(true))

//            try {
//                val accessResult = getAccess()
//                if (accessResult is ViewState.Success) {
//                    val userGoalResult = getUserGoals(accessResult.data.accessToken).first()
//
//                    if (userGoalResult is Resource.Success) {
//                        emitGoalsState(ViewState.Success(userGoalResult.data))
//                    } else {
//                        emitGoalsState(ViewState.Error("Erro ao buscar dados do usuário"))
//                    }
//                } else {
//                    emitGoalsState(ViewState.Error("Erro ao obter o token de acesso"))
//                }
//            } catch (e: Exception) {
//                emitGoalsState(ViewState.Error("Erro desconhecido: ${e.message}"))
//            }

        }
    }

    private suspend fun emitGoalsState(state: ViewState<List<User>>) {
        withContext(Dispatchers.Main) {
            _userGoalsLiveData.value = state
        }

    }

    //recuperando o token de acesso
    private suspend fun getAccess(): ViewState<Access> {

        val result = getAccessUseCase()

        return if (result is Resource.Success) {
            ViewState.Success(result.data)
        } else {
            ViewState.Error("Não foi possível localizar o token de acesso.")
        }
    }

    //chamando o use case
    private suspend fun getUserGoals(token: String): Flow<Resource<List<User>>> {
        return getUserUseCase(token)
    }

}



