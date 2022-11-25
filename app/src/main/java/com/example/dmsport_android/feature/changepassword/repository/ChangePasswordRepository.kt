package com.example.dmsport_android.feature.changepassword.repository

import com.example.dmsport_android.feature.register.model.DuplicateRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyRequest
import com.example.dmsport_android.feature.changepassword.model.ChagePasswordRequest
import com.example.dmsport_android.feature.changepassword.model.EmailChangePwRequest
import com.example.dmsport_android.network.changePwApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class ChangePasswordRepository {

    suspend fun emailChangePw(emailChangePwRequest: EmailChangePwRequest): Response<Void> =
        changePwApi.emailChangePw(emailChangePwRequest)


    suspend fun findVerifyEmail(duplicateRequest : DuplicateRequest): Response<Void> =
        changePwApi.findVerifyEmail(duplicateRequest)


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