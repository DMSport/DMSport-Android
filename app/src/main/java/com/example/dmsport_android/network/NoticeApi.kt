package com.example.dmsport_android.network

import com.example.dmsport_android.dto.response.RecentNoticeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NoticeApi {
    @GET("recent")
    suspend fun recentNotice(
        @Header("Authorization") accessToken : String,
    ) :Response<RecentNoticeResponse>
}