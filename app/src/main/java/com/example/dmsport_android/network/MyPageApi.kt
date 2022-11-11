package com.example.dmsport_android.network

import com.example.dmsport_android.dto.request.DeleteUserRequest
import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.response.MyPageResponse
import retrofit2.Response
import retrofit2.http.*

interface MyPageApi {
    @GET("users/my")
    suspend fun fetchMyInfo(
        @Header("Authorization") accessToken: String,
    ): Response<MyPageResponse>

    @DELETE("users/logout")
    suspend fun userLogout(
        @Header("Authorization") accessToken: String,
    ): Response<Void>

    @HTTP(method="DELETE", hasBody=true, path="users")
    suspend fun deleteUser(
        @Header("Authorization") accessToken: String,
        @Body deleteUserRequest: DeleteUserRequest,
    ): Response<Void>

    @PUT("users/password")
    suspend fun changePassword(
        @Body emailChangePwRequest: EmailChangePwRequest,
    ) : Response<Void>

    @POST("users/mail/find")
    suspend fun sendVerifyEmail(
        @Body findPwVerifyEmailRequest: FindPwVerifyEmailRequest,
    ) : Response<Void>
}