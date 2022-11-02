package com.example.dmsport_android.dto.request

data class VerifyRequest(
    val auth_code: String,
    val email: String,
)