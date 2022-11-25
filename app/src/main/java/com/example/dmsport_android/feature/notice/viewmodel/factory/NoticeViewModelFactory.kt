package com.example.dmsport_android.feature.notice.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.vote.repository.NoticeRepository

class NoticeViewModelFactory(
    private val noticeRepository: NoticeRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NoticeViewModel(noticeRepository) as T
}