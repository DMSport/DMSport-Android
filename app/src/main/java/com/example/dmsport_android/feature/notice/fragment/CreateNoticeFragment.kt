package com.example.dmsport_android.feature.notice.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.DialogCreateNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.notice.viewmodel.factory.NoticeViewModelFactory
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.*


class CreateNoticeFragment : DialogFragment(
    R.layout.dialog_create_notice
) {

    private lateinit var binding : DialogCreateNoticeBinding

    private val noticeRepository by lazy {
        NoticeRepository()
    }

    private val pref by lazy {
        initPref(requireContext())
    }

    private val noticeViewModelFactory by lazy {
        NoticeViewModelFactory(
            noticeRepository = noticeRepository,
            pref = pref,
        )
    }

    private val noticeViewModel by lazy {
        ViewModelProvider(this, noticeViewModelFactory).get(NoticeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.dialog_create_notice,
            container,
            false,
        )
        initCancelButton()
        initCompleteButton()
        observeCreateNoticeResponse()
        return binding.root
    }

    private fun initCompleteButton() {
        binding.btCreateNoticeComplete.setOnClickListener {
            if(
                binding.etCreateNoticeTitle.text.isNotEmpty()
                && binding.etCreateNoticeContent.text.isNotEmpty()
            ){
                noticeViewModel.createNotice(
                    binding.etCreateNoticeTitle.text.toString(),
                    binding.etCreateNoticeContent.text.toString(),
                )
            }
        }
    }

    private fun initCancelButton(){
        binding.tvCreateNoticeCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun observeCreateNoticeResponse() {
        noticeViewModel.createNoticeResponse.observe(viewLifecycleOwner) {
            Log.d("TEST", it.errorBody()?.string() ?: it.code().toString())
            when (it.code()) {
                CREATED -> {
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.create_notice_complete)
                    )
                    dismiss()
                }
                BAD_REQUEST -> {
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.register_bad_request)
                    )
                }
                FORBIDDEN -> {
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.create_notice_forbidden)
                    )
                }
            }
        }
    }
}
