package com.bnt.toastmasters.data.repository

import com.bnt.toastmasters.domain.User
import com.bnt.toastmasters.domain.UserRole
import com.bnt.toastmasters.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepositoryImpl : UserRepository {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    
    override suspend fun getCurrentUser(): User? {
        return _currentUser.value
    }
    
    override suspend fun saveUser(user: User) {
        _currentUser.value = user
    }
    
    override suspend fun clearUser() {
        _currentUser.value = null
    }
    
    override fun observeCurrentUser(): Flow<User?> {
        return _currentUser.asStateFlow()
    }
} 