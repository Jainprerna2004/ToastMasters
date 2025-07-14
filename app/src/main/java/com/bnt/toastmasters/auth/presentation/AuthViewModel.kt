package com.bnt.toastmasters.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bnt.toastmasters.auth.domain.AuthUseCases
import kotlinx.coroutines.launch
import com.bnt.toastmasters.auth.domain.AuthState

class AuthViewModel(private val useCases: AuthUseCases) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = useCases.login(email, password)
            _authState.value = result
        }
    }

    fun signup(name: String, email: String, mobile: String, level: String, dateOfJoining: String, password: String) {
        viewModelScope.launch {
            val result = useCases.signup(name, email, mobile, level, dateOfJoining, password)
            _authState.value = result
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            val result = useCases.forgotPassword(email)
            _authState.value = result
        }
    }

    fun checkSession() {
        viewModelScope.launch {
            val result = useCases.checkSession()
            _authState.value = result
        }
    }
}
