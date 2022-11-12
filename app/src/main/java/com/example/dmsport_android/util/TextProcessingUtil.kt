package com.example.dmsport_android.util

class TextProcessingUtil {

    fun changeText(text: String): String =
        when (text) {
            "LUNCH" -> LUNCH
            else -> DINNER
        }
}