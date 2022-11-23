package com.example.dmsport_android.network

import com.example.dmsport_android.dto.response.AllNoticeResponse
import com.example.dmsport_android.dto.response.RecentNoticeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NoticeApi {

    @GET("notices/recent")
    suspend fun recent(
        @Header("Authorization") accessToken : String,
    ): Response<RecentNoticeResponse>

    @GET("notices")
    suspend fun getAllNotice(
        @Header("Authorization") accessToken: String,
    ): Response<AllNoticeResponse>
}