package com.example.dmsport_android.dto.response

data class VoteListResponse(
    val is_ban: Boolean,
    val ban_period: Boolean,
    val max_people: Int,
    val vote: ArrayList<Vote>,
    val ban: Boolean,
)

data class Vote(
    val vote_id : Int,
    val vote_type : String,
    val vote_count : Int,
)
