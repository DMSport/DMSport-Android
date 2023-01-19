package com.example.dmsport_android.feature.login.model

data class LoginResponse(
    val access_token: String,
    val expired_at: String,
    val refresh_token: String,
)