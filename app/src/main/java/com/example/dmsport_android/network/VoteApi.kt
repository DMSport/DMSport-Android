package com.example.dmsport_android.network

import com.example.dmsport_android.dto.response.VoteListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VoteApi {
    @GET("clubs/vote")
    suspend fun getVoteList(
        @Header("Authorization") accessToken : String,
        @Query("type") type : String,
        @Query("date") date : String,
    ) : Response<VoteListResponse>
}