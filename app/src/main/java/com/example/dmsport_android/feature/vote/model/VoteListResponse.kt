package com.example.dmsport_android.dto.response

data class VoteListResponse(
    val ban_period: Boolean,
    val max_people: Int,
    val vote_list: ArrayList<Vote>,
    val ban: Boolean,
)

data class Vote(
    val vote_id: Int,
    val time: String,
    val vote_count: Int,
    val max_people: Int,
    val is_complete: Boolean,
    val vote_user: ArrayList<User>
)

data class User(
    val name: String,
    val team: Long,
)
