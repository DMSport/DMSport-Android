package com.example.dmsport_android.dto.response

data class RecentNoticeResponse(
    val manager: String,
    val admin: String,
)

data class Recent(
    val id: Int,
    val title: String,
    val content_preview: String,
    val type: String,
    val created_at: String,
)