package com.bnt.toastmasters.auth.data

import android.widget.Spinner
import com.bnt.toastmasters.auth.domain.AuthState

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthState
    suspend fun signup(name: String, email: String, mobile: String, level: String, dateOfJoining: String, userType: Spinner, password: String): AuthState
    suspend fun forgotPassword(email: String): AuthState
    suspend fun checkSession(): AuthState
} 