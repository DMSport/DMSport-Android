package com.example.dmsport_android.network

import com.example.dmsport_android.feature.refresh.RefreshResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.PUT

interface RefreshApi {
    @PUT("users/auth")
    suspend fun getRefreshToken(
        @Header("X-Refresh-Token") refreshToken : String,
    ) : Response<RefreshResponse>
}