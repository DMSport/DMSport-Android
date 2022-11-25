package com.example.dmsport_android.dto.response

data class VoteListResponse(
    val ban_period: Boolean,
    val max_people: Int,
    val vote: ArrayList<Vote>,
    val ban: Boolean,
)

data class Vote(
    val vote_id : Int,
    val time : String,
    val vote_count : Int,
)