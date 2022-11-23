package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.response.AllNoticeResponse
import com.example.dmsport_android.dto.response.RecentNoticeResponse
import com.example.dmsport_android.network.NoticeApi
import com.example.dmsport_android.network.noticeApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class NoticeRepository {
    suspend fun getRecentNotice(
    ): Response<RecentNoticeResponse> =
        noticeApi.recent(ACCESS_TOKEN)

    suspend fun getAllNotice(
    ): Response<AllNoticeResponse> =
        noticeApi.getAllNotice(ACCESS_TOKEN)

}