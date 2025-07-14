package com.bnt.toastmasters.auth.data

import com.bnt.toastmasters.auth.domain.AuthState
import com.bnt.toastmasters.auth.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun login(email: String, password: String): AuthState {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw Exception("User ID not found")
            val doc = firestore.collection("users").document(userId).get().await()
            val userType = doc.getString("userType") ?: "member"
            AuthState.SuccessWithRole(userType)
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Login failed")
        }
    }

    override suspend fun signup(name: String, email: String, mobile: String, level: String, dateOfJoining: String, password: String): AuthState {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw Exception("User ID not found")
            val user = User(name, email, mobile, level, dateOfJoining, userType = "member")
            firestore.collection("users").document(userId).set(user).await()
            AuthState.Success
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Signup failed")
        }
    }

    override suspend fun forgotPassword(email: String): AuthState {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            AuthState.PasswordResetSent
        } catch (e: Exception) {
            AuthState.Error(e.message ?: "Failed to send reset email")
        }
    }

    override suspend fun checkSession(): AuthState {
        return if (firebaseAuth.currentUser != null) AuthState.LoggedIn else AuthState.LoggedOut
    }
} 