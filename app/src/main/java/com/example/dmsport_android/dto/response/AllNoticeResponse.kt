package com.example.dmsport_android.dto.response

import java.text.SimpleDateFormat

data class AllNoticeResponse(
    val notices: ArrayList<AllNoticeList>
)

data class AllNoticeList(
    val id: Int,
    val type: String,
    val title: String,
    val content_preview: String,
    var created_at: String,
)

