package com.example.dmsport_android.feature.notice.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.vote.repository.NoticeRepository

@Suppress("UNCHECKED_CAST")
class NoticeViewModelFactory(
    private val noticeRepository: NoticeRepository,
    private val pref : SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NoticeViewModel(
            noticeRepository = noticeRepository,
            pref = pref,
        ) as T
}