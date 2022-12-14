package com.example.dmsport_android.feature.notice.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.notice.activity.NoticeActivity
import com.example.dmsport_android.feature.notice.adapter.NoticeAdapter
import com.example.dmsport_android.feature.notice.model.NoticeList
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.*


class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

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
        )[NoticeViewModel::class.java]
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
        noticeViewModel.getNoticeList()
        moreAllNotice()
        eventNotice()
        observeAllNoticeListResponse()
        observeDeleteNoticeResponse()
        observeEditNoticeResponse()
    }

    private fun moreAllNotice() {
        binding.tvNoticeMoreAll.setOnClickListener {
            noticeViewModel.setNoticeTypeFalse()
            startIntent(this.requireContext(), NoticeActivity::class.java)
        }
    }

    private fun eventNotice() {
        binding.tvNoticeEventAll.setOnClickListener {
            noticeViewModel.setNoticeTypeTrue()
            startIntent(requireContext(), NoticeActivity::class.java)
        }
    }

    private fun observeAllNoticeListResponse() {
        noticeViewModel.getNoticeResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                OK -> {
                    setNoticeRecyclerView(
                        allNoticeList = noticeViewModel.setAllNoticeList(it.body()!!.notices),
                        eventNoticeList = noticeViewModel.setEventNoticeList(it.body()!!.notices)
                    )
                }
            }
        }
    }

    private fun observeDeleteNoticeResponse(){
        noticeViewModel.deleteNoticeResponse.observe(viewLifecycleOwner){
            when(it.code()){
                NO_CONTENT ->{
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.delete_notice_success)
                    )
                    dialog.dismiss()
                }
                FORBIDDEN -> showSnackbarForbidden()
            }
        }
    }

    private fun showSnackbarForbidden(){
        showSnack(
            view = binding.root,
            message = getString(R.string.create_notice_forbidden)
        )
    }

    private fun setNoticeRecyclerView(
        allNoticeList: ArrayList<NoticeList>,
        eventNoticeList: ArrayList<NoticeList>,
    ) {
        setRecyclerView(
            rv = binding.rvNoticeAdmin,
            noticeList = allNoticeList,
        )
        setRecyclerView(
            rv = binding.rvNoticeManager,
            noticeList = eventNoticeList,
        )
    }

    private fun setRecyclerView(
        rv: RecyclerView,
        noticeList: ArrayList<NoticeList>,
    ) {
        rv.run {
            adapter = NoticeAdapter(
                noticeList = noticeList,
                context = requireContext(),
                editor = editor,
                noticeViewModel = noticeViewModel,
            )
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeEditNoticeResponse(){
        noticeViewModel.editNoticeResponse.observe(viewLifecycleOwner){
            when(it.code()){
                NO_CONTENT->{
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.edit_notice),
                    )
                    dialog.dismiss()
                }
                BAD_REQUEST->{
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.register_bad_request)
                    )
                }
                FORBIDDEN  -> showSnackbarForbidden()

            }
        }
    }
}
