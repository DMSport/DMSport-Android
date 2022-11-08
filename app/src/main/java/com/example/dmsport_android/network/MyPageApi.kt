package com.example.dmsport_android.network

import com.example.dmsport_android.dto.response.MyPageResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageApi {
    @GET("users/my")
    suspend fun my(
        @Header("Authorization") accessToken : String,
    ) : Response<MyPageResponse>

    @DELETE("users/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String,
    ) : Response<Void>
}