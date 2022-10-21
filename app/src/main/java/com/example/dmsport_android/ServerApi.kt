package com.example.dmsport_android

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<Void>
}