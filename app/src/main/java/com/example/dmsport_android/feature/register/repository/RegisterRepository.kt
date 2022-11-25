package com.example.dmsport_android.feature.register.repository

import com.example.dmsport_android.feature.register.model.DuplicateRequest
import com.example.dmsport_android.feature.register.model.RegisterRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyEmailRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyRequest
import com.example.dmsport_android.feature.register.model.RegisterResponse
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