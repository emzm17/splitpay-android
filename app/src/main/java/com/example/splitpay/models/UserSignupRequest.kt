package com.example.splitpay.models

data class UserSignupRequest(
    val email: String,
    val name: String,
    val password: String
)