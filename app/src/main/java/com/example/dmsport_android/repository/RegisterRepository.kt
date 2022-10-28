package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.network.ApiProvider
import retrofit2.Response

class RegisterRepository {

    suspend fun duplicate(duplicateRequest: DuplicateRequest) : Response<Void> {
        return ApiProvider.retrofit.duplicate(duplicateRequest)
    }

}