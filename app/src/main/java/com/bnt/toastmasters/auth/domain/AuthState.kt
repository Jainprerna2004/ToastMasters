package com.bnt.toastmasters.auth.domain

sealed class AuthState {
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
    object Loading : AuthState()
    object LoggedIn : AuthState()
    object LoggedOut : AuthState()
    object PasswordResetSent : AuthState()
} 