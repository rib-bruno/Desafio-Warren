package com.example.warrenlogin.feature_user.presentation


sealed class  ViewState<out T> {

    data class Success<out T>(val data: T) : ViewState<T>()

    data class Error( val error: String) : ViewState<Nothing>()

    data class Loading(val loading: Boolean) : ViewState<Nothing>()
}