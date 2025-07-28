package com.bnt.toastmasters.domain.repository

import com.bnt.toastmasters.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun saveUser(user: User)
    suspend fun clearUser()
    fun observeCurrentUser(): Flow<User?>
} 