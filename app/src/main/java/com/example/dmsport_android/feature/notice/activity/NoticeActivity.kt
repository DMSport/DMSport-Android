package com.example.dmsport_android.feature.notice.activity


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMoreAllNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.notice.adapter.NoticeAdapter
import com.example.dmsport_android.feature.notice.model.NoticeList
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.*
import kotlin.collections.ArrayList

class NoticeActivity : BaseActivity<ActivityMoreAllNoticeBinding>(
    R.layout.activity_more_all_notice,
) {

    private val noticeRepository: NoticeRepository by lazy {
        NoticeRepository()
    }

    private val noticeViewModelFactory: NoticeViewModelFactory by lazy {
        NoticeViewModelFactory(
            noticeRepository = noticeRepository,
            pref = pref,
        )
    }

    private val noticeViewModel: NoticeViewModel by lazy {
        ViewModelProvider(
            this,
            noticeViewModelFactory,
        ).get(NoticeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noticeViewModel.getNoticeList()
        observeAllNoticeListResponse()
        initCreateNoticeButton()
        initMoreAllNoticeActivity()
        initBackButton()
    }

    private fun initMoreAllNoticeActivity() {
        if (isAllEventNotice) {
            binding.tvNoticeAllAllNotice.text = getString(R.string.notice_tv_event)
        }
    }

    private fun observeAllNoticeListResponse() {
        noticeViewModel.getNoticeResponse.observe(this) {
            when (it.code()) {
                OK -> {
                    if (isAllEventNotice) {
                        initRecyclerView(noticeViewModel.setEventNoticeList(it.body()!!.notices))
                    } else {
                        initRecyclerView(noticeViewModel.setAllNoticeList(it.body()!!.notices))
                    }

                }
            }
        }
    }

    private fun initRecyclerView(allNoticeList: ArrayList<NoticeList>) {
        binding.rvNoticeAllNoticeList.run {
            adapter = NoticeAdapter(
                noticeList = allNoticeList,
                context = applicationContext,
                editor = editor,
                resources = resources,
                noticeViewModel = noticeViewModel,
            )
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    private fun initCreateNoticeButton() {
        if (noticeViewModel.checkUserAuth() && isAllEventNotice) {
            binding.fabNoticeAllCreate.run {
                visibility = View.VISIBLE
                setOnClickListener {
                    createNoticeDialog(
                        context = applicationContext,
                        noticeViewModel = noticeViewModel,
                    )
                }
            }
        }
    }

    private fun initBackButton() {
        binding.imgNoticeAllBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        noticeViewModel.setNoticeTypeFalse()
    }

}