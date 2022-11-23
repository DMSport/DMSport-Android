package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.ChagePasswordRequest
import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.network.changePwApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class ChangePasswordRepository {

    suspend fun emailChangePw(emailChangePwRequest: EmailChangePwRequest): Response<Void> =
        changePwApi.emailChangePw(emailChangePwRequest)


    suspend fun findVerifyEmail(findPwVerifyEmailRequest: FindPwVerifyEmailRequest): Response<Void> =
        changePwApi.findVerifyEmail(findPwVerifyEmailRequest)


    suspend fun verify(verifyRequest: VerifyRequest): Response<Void> =
        changePwApi.verify(verifyRequest)

    suspend fun changePassword(
        changePasswordRequest: ChagePasswordRequest,
    ) =
        changePwApi.changePassword(
            accessToken = ACCESS_TOKEN,
            changePasswordRequest = changePasswordRequest,
        )
}