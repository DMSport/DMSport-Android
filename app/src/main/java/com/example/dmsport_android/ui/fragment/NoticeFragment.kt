package com.example.dmsport_android.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentNoticeBinding
import com.example.dmsport_android.repository.NoticeRepository
import com.example.dmsport_android.ui.activity.MoreAllNoticeActivity
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.NoticeViewModel
import com.example.dmsport_android.viewmodel.factory.NoticeViewModelFactory


class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

    private val noticeRepository: NoticeRepository by lazy {
        NoticeRepository()
    }

    private val noticeViewModelFactory: NoticeViewModelFactory by lazy {
        NoticeViewModelFactory(noticeRepository)
    }

    private val noticeViewModel: NoticeViewModel by lazy {
        ViewModelProvider(this, noticeViewModelFactory).get(NoticeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = noticeViewModel

        binding.fragment = this
    }

    fun moreAllNotice() {
        startIntent(this.requireContext(), MoreAllNoticeActivity::class.java)
    }
}
