package com.example.dmsport_android.feature.refresh

import com.example.dmsport_android.feature.refresh.model.RefreshResponse
import com.example.dmsport_android.network.refreshApi
import com.example.dmsport_android.util.REFRESH_TOKEN
import retrofit2.Response

class RefreshRepository {

    suspend fun getRefreshToken() : Response<RefreshResponse> =
        refreshApi.getRefreshToken(
            refreshToken = REFRESH_TOKEN
        )
}