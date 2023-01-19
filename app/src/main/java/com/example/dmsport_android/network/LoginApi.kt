package com.example.dmsport_android.network

import com.example.dmsport_android.feature.login.model.LoginRequest
import com.example.dmsport_android.feature.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("users/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>
}