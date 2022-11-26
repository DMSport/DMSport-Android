package com.example.dmsport_android.feature.notice.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityDetailNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.noticeId

class DetailNoticeActivity : BaseActivity<ActivityDetailNoticeBinding>(
    R.layout.activity_detail_notice,
) {

    private val noticeRepository by lazy {
        NoticeRepository()
    }

    private val noticeViewModelFactory by lazy {
        NoticeViewModelFactory(
            noticeRepository = noticeRepository,
            pref = pref,
        )
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