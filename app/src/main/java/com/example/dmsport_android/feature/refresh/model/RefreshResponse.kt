package com.example.dmsport_android.feature.refresh.model

data class RefreshResponse(
    val access_token : String,
    val expired_at : String,
    val refresh_token : String,
)
