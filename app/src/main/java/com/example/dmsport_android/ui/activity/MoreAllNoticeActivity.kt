package com.example.dmsport_android.ui.activity


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.adapter.AllNoticeAdapter
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMoreAllNoticeBinding
import com.example.dmsport_android.dto.response.AllNoticeList
import com.example.dmsport_android.repository.NoticeRepository
import com.example.dmsport_android.util.OK
import com.example.dmsport_android.util.initPref
import com.example.dmsport_android.viewmodel.NoticeViewModel
import com.example.dmsport_android.viewmodel.factory.NoticeViewModelFactory
import kotlin.collections.ArrayList

class MoreAllNoticeActivity :
    BaseActivity<ActivityMoreAllNoticeBinding>(R.layout.activity_more_all_notice) {



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
        noticeViewModel.getAllNotice()
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
            adapter = AllNoticeAdapter(
                arrayList = allNoticeList,
                noticeViewModel = noticeViewModel,
                context = applicationContext,
                editor = pref.edit(),
            )
            layoutManager = LinearLayoutManager(applicationContext)
        }

    }
}