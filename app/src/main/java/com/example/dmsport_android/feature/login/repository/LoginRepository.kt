package com.example.dmsport_android.feature.login.repository

import com.example.dmsport_android.feature.login.model.LoginRequest
import com.example.dmsport_android.feature.login.model.LoginResponse
import com.example.dmsport_android.network.loginApi
import retrofit2.Response

class LoginRepository {

    suspend fun login(
        loginRequest: LoginRequest,
    ): Response<LoginResponse> =
        loginApi.login(loginRequest)
}