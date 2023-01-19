package com.example.dmsport_android.feature.notice.model

data class DetailNoticeResponse(
    val title: String,
    val content: String,
    val writer: String,
    val type : String,
    val created_at: String,
)