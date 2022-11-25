package com.example.dmsport_android.network

import com.example.dmsport_android.feature.notice.model.AllNoticeResponse
import com.example.dmsport_android.feature.notice.model.DetailNoticeResponse
import com.example.dmsport_android.feature.notice.model.RecentNoticeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NoticeApi {

    @GET("notices/recent")
    suspend fun recent(
        @Header("Authorization") accessToken : String,
    ): Response<RecentNoticeResponse>

    @GET("notices")
    suspend fun getAllNotice(
        @Header("Authorization") accessToken: String,
    ): Response<AllNoticeResponse>

    @GET("notices/{notice-id}")
    suspend fun getDetailNotice(
        @Header("Authorization") accessToken: String,
        @Path("notice-id") noticeId : Long,
    ) : Response<DetailNoticeResponse>
}