package com.example.dmsport_android.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.databinding.DialogCreateNoticeBinding
import com.example.dmsport_android.databinding.DialogDeleteNoticeBinding
import com.example.dmsport_android.databinding.DialogParticipantsBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.feature.vote.adapter.VoteParticipantsAdapter
import com.example.dmsport_android.feature.vote.model.User
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel

lateinit var dialog: Dialog

fun createNoticeDialog(
    context: Context,
    noticeViewModel: NoticeViewModel,
) {
    val binding by lazy {
        DialogCreateNoticeBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    initDialog(
        context = context,
        view = binding.root,
    )

    binding.run {
        tvCreateNoticeCancel.setOnClickListener {
            dialog.dismiss()
        }
        btCreateNoticeComplete.setOnClickListener {
            if (binding.etCreateNoticeTitle.text.isNotEmpty()
                && binding.etCreateNoticeContent.text.isNotEmpty()
            ) {
                noticeViewModel.createNotice(
                    title = binding.etCreateNoticeTitle.text.toString(),
                    content = binding.etCreateNoticeContent.text.toString()
                )
            }
        }
    }
}

fun createDeleteNoticeDialog(
    context: Context,
    noticeViewModel: NoticeViewModel,
) {
    val binding by lazy {
        DialogDeleteNoticeBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    initDialog(
        context = context,
        view = binding.root,
    )

    binding.run {
        btDeleteNoticeComplete.setOnClickListener {
            noticeViewModel.deleteNotice(noticeViewModel.loadNoticeId())
        }

        tvDeleteNoticeCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}

fun createEditNoticeDialog(
    context: Context,
    noticeViewModel: NoticeViewModel,
) {
    val binding by lazy {
        DialogCreateNoticeBinding.inflate(
            LayoutInflater.from(context)
        )
    }
    initDialog(
        context = context,
        view = binding.root,
    )
    binding.run {
        etCreateNoticeTitle.run {
            hint = noticeViewModel.loadNoticeTitle()
        }
        etCreateNoticeContent.run {
            hint = noticeViewModel.loadNoticeContent()
        }

        btCreateNoticeComplete.setOnClickListener {
            if (etCreateNoticeTitle.text.isNotEmpty()
                && etCreateNoticeContent.text.isNotEmpty()
            ){
                noticeViewModel.editNotice(
                    title = etCreateNoticeTitle.text.toString(),
                    content = etCreateNoticeContent.text.toString(),
                )
            }
        }
        tvCreateNoticeCancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}

fun initDialog(
    context: Context,
    view: View,
) {

    dialog = Dialog(context).apply {

        setContentView(view)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }
}

fun createParticipantsDialog(
    context : Context,
    arrayList : ArrayList<User>?,
    voteListViewModel : VoteListViewModel
){

    val binding by lazy {
        DialogParticipantsBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    binding.btDialogParticipantsCheck.setOnClickListener {
        dialog.dismiss()
    }

    binding.rvDialogParticipantsTeam1.run {
        adapter = VoteParticipantsAdapter(
            participantsList = voteListViewModel.setFirstList(arrayList)
        )
        layoutManager = LinearLayoutManager(context)
    }

    binding.rvDialogParticipantsTeam2.run {
        adapter = VoteParticipantsAdapter(
            participantsList = voteListViewModel.setSecondList(arrayList),
        )
        layoutManager = LinearLayoutManager(context)
    }

    initDialog(
        context = context,
        view = binding.root
    )
}
