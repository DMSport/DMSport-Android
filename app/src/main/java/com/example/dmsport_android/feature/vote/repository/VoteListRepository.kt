package com.example.dmsport_android.feature.vote.repository

import com.example.dmsport_android.feature.vote.model.VoteListResponse
import com.example.dmsport_android.network.voteApi
import com.example.dmsport_android.util.ACCESS_TOKEN
import retrofit2.Response

class VoteListRepository {
    suspend fun getVoteList(
        type : String,
    ) : Response<VoteListResponse> =
        voteApi.getVoteList(
            ACCESS_TOKEN,
            type,
        )

    suspend fun vote(
        voteId : Long,
    ) : Response<Void> =
        voteApi.vote(
            accessToken = ACCESS_TOKEN,
            voteId = voteId,
        )
}