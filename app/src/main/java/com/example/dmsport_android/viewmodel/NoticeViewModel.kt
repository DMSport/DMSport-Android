package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.dmsport_android.repository.NoticeRepository

class NoticeViewModel (
    private val NoticeRepository: NoticeRepository,
    private val pref: SharedPreferences,
): ViewModel(){

}