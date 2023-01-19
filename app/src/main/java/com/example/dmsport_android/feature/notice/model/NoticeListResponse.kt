package com.example.dmsport_android.feature.notice.model

data class NoticeListResponse(
    val notices: ArrayList<NoticeList>
)

data class NoticeList(
    val id: Int,
    val type: String,
    val title: String,
    val content_preview: String,
    var created_at: String,
)

