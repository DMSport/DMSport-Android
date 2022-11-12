package com.example.dmsport_android.util

class ConvertTextUtil {
    fun convertString(vote_type: String?) =
        when (vote_type) {
            LUNCH -> "점심시간"
            else -> "저녁시간"

        }

    fun convertInt(vote_count : Int, max_people : Int) =
        "$vote_count/${max_people}명"
}