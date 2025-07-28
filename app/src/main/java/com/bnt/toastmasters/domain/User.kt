package com.bnt.toastmasters.domain

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val level: String = "",
    val dateOfJoining: String = ""
) 