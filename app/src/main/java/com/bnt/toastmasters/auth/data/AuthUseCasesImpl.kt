package com.bnt.toastmasters.auth.data

import com.bnt.toastmasters.auth.domain.AuthState
import com.bnt.toastmasters.auth.domain.AuthUseCases

class AuthUseCasesImpl(private val repository: AuthRepository) : AuthUseCases {
    override suspend fun login(email: String, password: String): AuthState =
        repository.login(email, password)

    override suspend fun signup(name: String, email: String, mobile: String, level: String, dateOfJoining: String, password: String): AuthState =
        repository.signup(name, email, mobile, level, dateOfJoining, password)

    override suspend fun forgotPassword(email: String): AuthState =
        repository.forgotPassword(email)

    override suspend fun checkSession(): AuthState =
        repository.checkSession()
} 