package com.example.warrenlogin.feature_login.domain.use_case

import com.example.warrenlogin.feature_login.domain.entities.Access
import com.example.warrenlogin.feature_login.domain.repository.LoginRepository
import com.example.warrenlogin.feature_login.domain.util.Resource


class AccessUseCase(
    private val repository: LoginRepository
)  {
    suspend operator fun invoke() : Resource<Access> {
        return repository.haveAccess()
    }

}