package com.example.dmsport_android.feature.register.model

data class RegisterResponse(
    val access_token: String,
    val expired_at: String,
    val refresh_token: String,
    val user_authority: String,
)