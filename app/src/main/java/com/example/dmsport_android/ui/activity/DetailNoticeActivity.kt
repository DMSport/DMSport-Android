package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityDetailNoticeBinding
import com.example.dmsport_android.repository.NoticeRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.noticeId
import com.example.dmsport_android.viewmodel.NoticeViewModel
import com.example.dmsport_android.viewmodel.factory.NoticeViewModelFactory

class DetailNoticeActivity : BaseActivity<ActivityDetailNoticeBinding>(
    R.layout.activity_detail_notice,
) {

    private val noticeRepository by lazy {
        NoticeRepository()
    }

    private val noticeViewModelFactory by lazy {
        NoticeViewModelFactory(noticeRepository)
    }

    private val noticeViewModel by lazy {
        ViewModelProvider(
            this,
            noticeViewModelFactory,
        ).get(NoticeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = noticeViewModel
        binding.lifecycleOwner = this
        noticeViewModel.getDetailNotice(
            getPref(
                preferences = pref,
                key = noticeId,
                value = 0
            ) as Int
        )
        initBackButton()
    }

    private fun initBackButton() {
        binding.imgDetailBack.setOnClickListener {
            finish()
        }
    }
}