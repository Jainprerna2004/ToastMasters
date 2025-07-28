package com.bnt.toastmasters.auth.domain

import android.widget.Spinner

interface AuthUseCases {
    suspend fun login(email: String, password: String): AuthState
    suspend fun signup(name: String, email: String, mobile: String, level: String, dateOfJoining: String, userType: String, password: String): AuthState
    suspend fun forgotPassword(email: String): AuthState
    suspend fun checkSession(): AuthState
} 