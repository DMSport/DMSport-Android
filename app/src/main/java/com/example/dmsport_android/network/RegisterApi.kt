package com.example.dmsport_android.network

import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.dto.request.RegisterRequest
import com.example.dmsport_android.dto.request.VerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {

    @POST("users/mail/duplicate")
    suspend fun emailDuplicate(
        @Body duplicateRequest: DuplicateRequest,
    ): Response<Void>

    @POST("users/mail/signup")
    suspend fun sendVerifyEmail(
        @Body verifyEmailRequest: VerifyEmailRequest,
    ): Response<Void>

    @POST("users/mail/verify")
    suspend fun verifyEmail(
        @Body verifyRequest: VerifyRequest,
    ): Response<Void>

    @POST("users")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>
}