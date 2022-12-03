package com.example.dmsport_android.network

import com.example.dmsport_android.feature.vote.model.VoteListResponse
import retrofit2.Response
import retrofit2.http.*

interface VoteApi {
    @GET("clubs/vote")
    suspend fun getVoteList(
        @Header("Authorization") accessToken : String,
        @Query("type") type : String,
    ) : Response<VoteListResponse>

    @POST("clubs/vote/{vote-id}")
    suspend fun vote(
        @Header("Authorization") accessToken: String,
        @Path("vote-id") voteId : Long,
    ) : Response<Void>
}