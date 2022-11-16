package com.example.dmsport_android.dto.request

data class VerifyRequest(
    val email: String,
    val auth_code: String,
)