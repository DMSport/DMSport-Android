package com.example.dmsport_android.network

import com.example.dmsport_android.dto.request.*
import com.example.dmsport_android.dto.response.LoginResponse
import com.example.dmsport_android.dto.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ServerApi {
    @POST("users/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @POST("users/mail/duplicate")
    suspend fun duplicate(
        @Body duplicateRequest: DuplicateRequest
    ) : Response<Void>

    @POST("users/mail/signup")
    suspend fun verifyEmail(
        @Body verifyEmailRequest: VerifyEmailRequest
    ) : Response<Void>

    @POST("users/mail/verify")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest
    ) : Response<Void>

    @POST("users")
    suspend fun register(
        @Body registerRequest : RegisterRequest
    ) : Response<RegisterResponse>

    @POST("users/mail/find")
    suspend fun findVerifyEmail(
        @Body findPwVerifyEmailRequest: FindPwVerifyEmailRequest
    ) : Response<Void>

    @PUT("users/password")
    suspend fun emailChangePw(
        @Body emailChangePwRequest: EmailChangePwRequest
    ) : Response<Void>

}