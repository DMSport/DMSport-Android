package com.example.dmsport_android.network

import com.example.dmsport_android.feature.notice.model.*
import retrofit2.Response
import retrofit2.http.*

interface NoticeApi {
    @GET("notices")
    suspend fun getAllNotice(
        @Header("Authorization") accessToken: String,
    ): Response<NoticeListResponse>

    @GET("notices/{notice-id}")
    suspend fun getDetailNotice(
        @Header("Authorization") accessToken: String,
        @Path("notice-id") noticeId : Long,
    ) : Response<DetailNoticeResponse>

    @POST("notices/club")
    suspend fun createNotice(
        @Header("Authorization") accessToken: String,
        @Body createNoticeRequest: CreateNoticeRequest,
    ) : Response<Void>

    @DELETE("notices/{notice-id}")
    suspend fun deleteNotice(
        @Header("Authorization") accessToken: String,
        @Path("notice-id") noticeId : Long,
    ) : Response<Void>
}