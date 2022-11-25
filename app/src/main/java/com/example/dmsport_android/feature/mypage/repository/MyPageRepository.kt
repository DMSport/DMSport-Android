package com.example.dmsport_android.feature.mypage.repository

import com.example.dmsport_android.feature.deleteuser.DeleteUserRequest
import com.example.dmsport_android.feature.mypage.model.MyPageResponse
import com.example.dmsport_android.network.myPageApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class MyPageRepository {

    suspend fun fetchMyPage(): Response<MyPageResponse> =
        myPageApi.fetchMyInfo(ACCESS_TOKEN)

    suspend fun userLogout(): Response<Void> =
        myPageApi.userLogout(ACCESS_TOKEN)

    suspend fun deleteUser(
        deleteUserRequest: DeleteUserRequest,
    ): Response<Void> =
        myPageApi.deleteUser(
            ACCESS_TOKEN,
            deleteUserRequest,
        )


}