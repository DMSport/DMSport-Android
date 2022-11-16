package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.network.changePwApi
import com.example.dmsport_android.network.myPageApi
import com.example.dmsport_android.network.registerApi
import retrofit2.Response

class EmailChangePwRepository {

    suspend fun emailChangePw(emailChangePwRequest: EmailChangePwRequest): Response<Void> =
        changePwApi.emailChangePw(emailChangePwRequest)


    suspend fun findVerifyEmail(findPwVerifyEmailRequest: FindPwVerifyEmailRequest): Response<Void> =
        changePwApi.findVerifyEmail(findPwVerifyEmailRequest)


    suspend fun verify(verifyRequest: VerifyRequest): Response<Void> =
        changePwApi.verify(verifyRequest)
}