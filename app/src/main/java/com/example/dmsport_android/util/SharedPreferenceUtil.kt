package com.example.dmsport_android.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

val loginVisible = "LoginVisible"
val registerVisible = "RegisterVisible"
val registerVisibleRe = "RegisterVisibleRe"

val emailChangePwVisible = "EmailChangePwVisible"
val emailChangePwVisibleRe = "EmailChangePwVisibleRe"

val localEmail = "Email"
val localName = "Name"
val localPassword = "Password"

val selectedNumber = "SelectedNumber"

val BASKETBALL = "BASKETBALL"
val SOCCER = "SOCCER"
val BADMINTON = "BADMINTON"
val VOLLEYBALL = "VOLLEYBALL"

val LUNCH = "점심"
val DINNER = "저녁"

var isLogged = false
var isLogOuted = false
var isDeletedUser = false
var isJoined = false

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