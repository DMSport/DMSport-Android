package com.example.dmsport_android.network

import com.example.dmsport_android.dto.request.ChagePasswordRequest
import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import retrofit2.Response
import retrofit2.http.*

interface ChangePwApi {
    @POST("users/mail/verify")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest
    ) : Response<Void>

    @POST("users/mail/find")
    suspend fun findVerifyEmail(
        @Body findPwVerifyEmailRequest: FindPwVerifyEmailRequest
    ) : Response<Void>

    @PUT("users/password")
    suspend fun emailChangePw(
        @Body emailChangePwRequest: EmailChangePwRequest
    ) : Response<Void>

    @PATCH("users/password")
    suspend fun changePassword(
        @Header("Authorization") accessToken : String,
        @Body changePasswordRequest: ChagePasswordRequest,
    ) : Response<Void>
}