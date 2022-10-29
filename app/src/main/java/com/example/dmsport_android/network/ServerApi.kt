package com.example.dmsport_android.network

import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.dto.request.LoginRequest
import com.example.dmsport_android.dto.request.RegisterRequest
import com.example.dmsport_android.dto.request.VerifyEmailRequest
import com.example.dmsport_android.dto.response.LoginResponse
import com.example.dmsport_android.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("users/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @POST("users")
    suspend fun register(
        @Body registerRequest : RegisterRequest
    ) : Response<Response<RegisterResponse>>

    @POST("users/mail/duplicate")
    suspend fun duplicate(
        @Body duplicateRequest: DuplicateRequest
    ) : Response<Void>

    @POST("users/mail/signup")
    suspend fun verifyEmail(
        @Body verifyEmailRequest: VerifyEmailRequest
    ) : Response<Void>

}