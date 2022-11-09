package com.example.dmsport_android.repository

import android.util.Log
import com.example.dmsport_android.dto.response.MyPageResponse
import com.example.dmsport_android.network.ApiProvider
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class MyPageRepository {

    suspend fun my() : Response<MyPageResponse> {
        return ApiProvider.myPageApi.my(ACCESS_TOKEN)
    }

    suspend fun logout() : Response<Void>{
        return ApiProvider.myPageApi.logout(ACCESS_TOKEN)
    }

}