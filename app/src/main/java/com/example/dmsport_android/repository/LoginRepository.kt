package com.example.dmsport_android.repository

import android.util.Log
import com.example.dmsport_android.dto.request.LoginRequest
import com.example.dmsport_android.dto.response.LoginResponse
import com.example.dmsport_android.network.loginApi
import retrofit2.Response

class LoginRepository {

    suspend fun login(
        loginRequest: LoginRequest,
    ): Response<LoginResponse> =
        loginApi.login(loginRequest)
}