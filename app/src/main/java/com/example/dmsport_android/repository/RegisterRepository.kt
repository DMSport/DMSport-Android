package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.dto.request.RegisterRequest
import com.example.dmsport_android.dto.request.VerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.dto.response.RegisterResponse
import com.example.dmsport_android.network.registerApi
import retrofit2.Response

class RegisterRepository {

    suspend fun emailDuplicate(duplicateRequest: DuplicateRequest) : Response<Void> =
        registerApi.emailDuplicate(duplicateRequest)


    suspend fun sendVerifyEmail(verifyEmailRequest : VerifyEmailRequest) : Response<Void> =
        registerApi.sendVerifyEmail(verifyEmailRequest)


    suspend fun verifyEmail(verifyRequest: VerifyRequest) : Response<Void> =
        registerApi.verifyEmail(verifyRequest)


    suspend fun register(registerRequest: RegisterRequest) : Response<RegisterResponse> =
        registerApi.register(registerRequest)
}