package com.example.dmsport_android.feature.notice.model

data class RecentNoticeResponse(
    val manager: ArrayList<Manager>,
    val admin: ArrayList<Admin>,
)

data class Manager(
    val id: Int,
    val title: String,
    val content_preview: String,
    val type: String,
    val created_at: String,
)

data class Admin(
    val id: Int,
    val title: String,
    val content_preview: String,
    val type: String,
    val created_at: String,
)