package com.example.dmsport_android.feature.notice.model

data class RecentNoticeResponse(
    val manager: ArrayList<Notice>,
    val admin: ArrayList<Notice>,
)

data class Notice(
    val id: Int,
    val title: String,
    val content_preview: String,
    val type: String,
    val created_at: String,
)