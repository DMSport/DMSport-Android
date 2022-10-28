package com.example.dmsport_android.dto.response

data class RegisterResponse(
    val access_token: String,
    val expired_at: String,
    val refresh_token: String,
    val user_authority: String
)