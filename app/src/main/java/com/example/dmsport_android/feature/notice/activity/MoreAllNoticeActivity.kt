package com.example.dmsport_android.feature.notice.activity


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMoreAllNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.notice.model.AllNoticeList
import com.example.dmsport_android.feature.notice.adapter.NoticeAdapter
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.OK
import kotlin.collections.ArrayList

class MoreAllNoticeActivity : BaseActivity<ActivityMoreAllNoticeBinding>(
    R.layout.activity_more_all_notice,
) {

    private val noticeRepository: NoticeRepository by lazy {
        NoticeRepository()
    }

    private val noticeViewModelFactory: NoticeViewModelFactory by lazy {
        NoticeViewModelFactory(noticeRepository)
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
    }

    private fun observeAllNoticeListResponse() {
        noticeViewModel.allNoticeResponse.observe(this) {
            when (it.code()) {
                OK -> {
                    initRecyclerView(it.body()!!.notices)
                }
            }
        }
    }

    private fun initRecyclerView(allNoticeList: ArrayList<AllNoticeList>) {
        binding.rvNoticeAllNoticeList.run {
            adapter = NoticeAdapter(
                allNoticeList = allNoticeList,
                context = applicationContext,
                editor = pref.edit(),
            )
            layoutManager = LinearLayoutManager(applicationContext)
        }

    }
}