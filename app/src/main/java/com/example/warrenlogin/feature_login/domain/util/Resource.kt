package com.example.warrenlogin.feature_login.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

//sealed class Resource<out T: Any> {
//
//    data class Success<out T: Any>(val data: T) : Resource<T>()
//
//    data class Error(val error: Exception) : Resource<Nothing>()
//
//    object Loading : Resource<Nothing>()
//
//}
