package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.dto.request.RegisterRequest
import com.example.dmsport_android.dto.request.VerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.dto.response.RegisterResponse
import com.example.dmsport_android.network.ApiProvider
import retrofit2.Response

class RegisterRepository {

    suspend fun duplicate(duplicateRequest: DuplicateRequest) : Response<Void> {
        return ApiProvider.retrofit.duplicate(duplicateRequest)
    }

    suspend fun verifyEmail(verifyEmailRequest : VerifyEmailRequest) : Response<Void>{
        return ApiProvider.retrofit.verifyEmail(verifyEmailRequest)
    }

    suspend fun verify(verifyRequest: VerifyRequest) : Response<Void>{
        return ApiProvider.retrofit.verify(verifyRequest)
    }

    suspend fun register(registerRequest: RegisterRequest) : Response<RegisterResponse> {
        return ApiProvider.retrofit.register(registerRequest)
    }

}