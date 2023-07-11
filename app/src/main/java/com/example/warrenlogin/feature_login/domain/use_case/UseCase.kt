package com.example.warrenlogin.feature_login.domain.use_case

interface UseCase<out T, in Param> {
    suspend fun invoke (param: Param) : T
}

interface UseCaseNoParam<out T> {
    suspend fun execute() : T
}