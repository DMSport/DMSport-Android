package com.example.dmsport_android.network

import com.example.dmsport_android.feature.register.model.DuplicateRequest
import com.example.dmsport_android.feature.changepassword.model.ChagePasswordRequest
import com.example.dmsport_android.feature.changepassword.model.EmailChangePwRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyRequest
import retrofit2.Response
import retrofit2.http.*

interface ChangePwApi {
    @POST("users/mail/verify")
    suspend fun verify(
        @Body verifyRequest: VerifyRequest,
    ) : Response<Void>

    @POST("users/mail/find")
    suspend fun findVerifyEmail(
        @Body duplicateRequest : DuplicateRequest,
    ) : Response<Void>

    @PUT("users/password")
    suspend fun emailChangePw(
        @Body emailChangePwRequest: EmailChangePwRequest,
    ) : Response<Void>

    @PATCH("users/password")
    suspend fun changePassword(
        @Header("Authorization") accessToken : String,
        @Body changePasswordRequest: ChagePasswordRequest,
    ) : Response<Void>
}