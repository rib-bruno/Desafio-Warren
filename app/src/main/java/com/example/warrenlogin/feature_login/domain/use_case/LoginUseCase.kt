package com.example.warrenlogin.feature_login.domain.use_case

import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {

    suspend operator fun invoke(email: String, password: String): Resource<Access> {
        return repository.doLogin(email, password)
    }
}