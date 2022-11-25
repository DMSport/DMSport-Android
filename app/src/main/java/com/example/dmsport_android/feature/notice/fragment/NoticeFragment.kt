package com.example.dmsport_android.feature.notice.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.notice.activity.MoreAllNoticeActivity
import com.example.dmsport_android.feature.notice.adapter.AdminNoticeAdapter
import com.example.dmsport_android.feature.notice.model.Admin
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.startIntent


class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(
            view,
            savedInstanceState,
        )
        binding.viewmodel = noticeViewModel
        moreAllNotice()
        observeAdminNoticeResponse()
        noticeViewModel.getRecentNoticeList()
    }

    fun moreAllNotice() {
        binding.tvNoticeMoreAll.setOnClickListener {
            startIntent(this.requireContext(), MoreAllNoticeActivity::class.java)
        }
    }

    private fun observeAdminNoticeResponse(){
        noticeViewModel.recentNoticeResponse.observe(viewLifecycleOwner){
            when(it.code()){
                200-> initAdminNoticeRecyclerView(it.body()!!.admin)
            }
        }
    }

    private fun initAdminNoticeRecyclerView(adminNoticeList : ArrayList<Admin>){
        binding.rvNoticeAdmin.run {
            adapter = AdminNoticeAdapter(adminNoticeList)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}
