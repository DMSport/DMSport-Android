package com.example.dmsport_android.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.NoticeRepository
import com.example.dmsport_android.viewmodel.NoticeViewModel

class NoticeViewModelFactory(
    private val noticeRepository: NoticeRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NoticeViewModel(noticeRepository) as T
}