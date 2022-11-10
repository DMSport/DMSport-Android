package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.DeleteUserRequest
import com.example.dmsport_android.dto.response.MyPageResponse
import com.example.dmsport_android.network.ApiProvider
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class MyPageRepository {

    suspend fun fetchMyPage(): Response<MyPageResponse> =
        ApiProvider.myPageApi.fetchMyInfo(ACCESS_TOKEN)

    suspend fun userLogout(): Response<Void> =
        ApiProvider.myPageApi.userLogout(ACCESS_TOKEN)

    suspend fun deleteUser(
        deleteUserRequest: DeleteUserRequest,
    ): Response<Void> =
        ApiProvider.myPageApi.deleteUser(
            ACCESS_TOKEN,
            deleteUserRequest,
        )


}