package com.bnt.toastmasters.auth.domain

data class User(
    val name: String,
    val email: String,
    val mobile: String,
    val level: String,
    val dateOfJoining: String,
    val userType: String = "member"
) 