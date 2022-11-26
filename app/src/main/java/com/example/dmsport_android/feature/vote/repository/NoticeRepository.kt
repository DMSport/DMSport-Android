package com.example.dmsport_android.feature.vote.repository

import com.example.dmsport_android.feature.notice.model.CreateNoticeRequest
import com.example.dmsport_android.feature.notice.model.DetailNoticeResponse
import com.example.dmsport_android.feature.notice.model.NoticeListResponse
import com.example.dmsport_android.network.noticeApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class NoticeRepository {
    suspend fun getAllNotice(
    ): Response<NoticeListResponse> =
        noticeApi.getAllNotice(ACCESS_TOKEN)

    suspend fun getDetailNotice(
        noticeId : Long,
    ): Response<DetailNoticeResponse> =
        noticeApi.getDetailNotice(
            accessToken = ACCESS_TOKEN,
            noticeId = noticeId,
        )

    suspend fun createNotice(
        createNoticeRequest: CreateNoticeRequest,
    ) : Response<Void> =
        noticeApi.createNotice(
            accessToken = ACCESS_TOKEN,
            createNoticeRequest = createNoticeRequest,
        )
}