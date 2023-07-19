package com.example.warrenlogin.feature_user.presentation

import com.example.warrenlogin.feature_user.domain.entities.User


sealed class  ViewState<out T> {

    data class Success<out T>(val data: List<User>) : ViewState<T>()

    data class Error( val error: String) : ViewState<Nothing>()

    data class Loading(val loading: Boolean) : ViewState<Nothing>()
}