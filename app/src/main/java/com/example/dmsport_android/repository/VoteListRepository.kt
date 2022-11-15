package com.example.dmsport_android.repository

import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.network.voteApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class VoteListRepository {
    suspend fun getVoteList(
        type : String,
        date : String,
    ) : Response<VoteListResponse> =
        voteApi.getVoteList(
            ACCESS_TOKEN,
            type,
            date,
        )
}