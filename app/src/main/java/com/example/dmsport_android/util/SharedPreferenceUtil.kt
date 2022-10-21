package com.example.dmsport_android

import android.content.Context
import android.content.SharedPreferences

fun initPref(context: Context, mode: Int): SharedPreferences {
    return context.getSharedPreferences("user", mode)
}


fun putPref(editor: SharedPreferences.Editor, key: String, value: Any?) {
    when(value){
        is String -> editor.putString(key, value).apply()
        is Int -> editor.putInt(key, value).apply()
        is Boolean -> editor.putBoolean(key, value).apply()
    }
}

fun getPref(preferences: SharedPreferences, key: String, value: Any?) : Any?{
    return when(value){
        is String -> preferences.getString(key, value)
        is Int -> preferences.getInt(key, value)
        else -> preferences.getBoolean(key, value as Boolean)
    }
}