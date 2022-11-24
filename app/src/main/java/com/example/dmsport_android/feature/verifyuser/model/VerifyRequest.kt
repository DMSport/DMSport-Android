package com.example.dmsport_android.feature.verifyuser.model

data class VerifyRequest(
    val email: String,
    val auth_code: String,
)