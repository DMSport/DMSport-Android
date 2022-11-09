package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.response.MyPageResponse
import com.example.dmsport_android.network.ApiProvider
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class MyPageRepository {

    suspend fun fetchMyPage() : Response<MyPageResponse> =
        ApiProvider.myPageApi.my(ACCESS_TOKEN)


    suspend fun logout() : Response<Void> =
        ApiProvider.myPageApi.logout(ACCESS_TOKEN)




}