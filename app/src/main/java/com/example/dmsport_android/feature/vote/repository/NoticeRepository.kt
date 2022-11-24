package com.example.dmsport_android.feature.vote.repository

import com.example.dmsport_android.feature.notice.model.AllNoticeResponse
import com.example.dmsport_android.feature.notice.model.DetailNoticeResponse
import com.example.dmsport_android.feature.notice.model.RecentNoticeResponse
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

    suspend fun getDetailNotice(
        noticeId : Long,
    ): Response<DetailNoticeResponse> =
        noticeApi.getDetailNotice(
            accessToken = ACCESS_TOKEN,
            noticeId = noticeId,
        )

}