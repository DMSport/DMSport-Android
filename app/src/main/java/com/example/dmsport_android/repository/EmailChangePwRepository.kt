package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.network.ApiProvider
import retrofit2.Response

class EmailChangePwRepository {

    suspend fun emailChangePw(emailChangePwRequest: EmailChangePwRequest): Response<Void> {
        return ApiProvider.retrofit.emailChangePw(emailChangePwRequest)
    }

    suspend fun findVerifyEmail(findPwVerifyEmailRequest: FindPwVerifyEmailRequest): Response<Void> {
        return ApiProvider.retrofit.findVerifyEmail(findPwVerifyEmailRequest)
    }

    suspend fun verify(verifyRequest: VerifyRequest): Response<Void> {
        return ApiProvider.retrofit.verify(verifyRequest)
    }
}