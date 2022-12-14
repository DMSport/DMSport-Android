package com.example.dmsport_android.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

const val loginVisible = "LoginVisible"
const val registerVisible = "RegisterVisible"
const val registerVisibleRe = "RegisterVisibleRe"

const val localEmail = "Email"
const val localName = "Name"
const val localPassword = "Password"

const val selectedVoteNumber = "SelectedNumber"

const val LUNCH = "LUNCH"

const val notice_Id = "NoticeId"
const val notice_title = "NoticeTitle"
const val notice_content = "NoticeContent"

const val isManaged = "isManaged"
const val isApplyed = "isApplyed"

var isLogged = false
var isLogOuted = false
var isDeletedUser = false
var isJoined = false
var isVerified = false
var isAllEventNotice = false
var isResume = false

val typeList = arrayListOf("BADMINTON", "SOCCER", "BASKETBALL", "VOLLEYBALL")
val typeListTitle = arrayListOf("배드민턴", "축구", "농구", "배구")

fun initPref(
    context: Context,
): SharedPreferences =
    context.getSharedPreferences("user", MODE_PRIVATE)


fun putPref(
    editor: SharedPreferences.Editor,
    key: String,
    value: Any?,
) {
    when (value) {
        is String -> editor.putString(key, value).apply()
        is Int -> editor.putInt(key, value).apply()
        is Boolean -> editor.putBoolean(key, value).apply()
    }
}

fun getPref(
    preferences: SharedPreferences,
    key: String,
    value: Any?,
): Any? =
    when (value) {
        is String -> preferences.getString(key, value)
        is Int -> preferences.getInt(key, value)
        else -> preferences.getBoolean(key, value as Boolean)
    }