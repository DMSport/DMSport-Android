package com.example.dmsport_android.network

import com.example.dmsport_android.feature.register.model.DuplicateRequest
import com.example.dmsport_android.feature.register.model.RegisterRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyEmailRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyRequest
import com.example.dmsport_android.feature.register.model.RegisterResponse
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