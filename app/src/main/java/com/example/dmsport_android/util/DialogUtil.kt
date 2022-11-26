package com.example.dmsport_android.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.dmsport_android.databinding.DialogCreateNoticeBinding
import com.example.dmsport_android.databinding.DialogDeleteNoticeBinding
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel

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

    dialog = Dialog(context).apply {
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }

    binding.tvCreateNoticeCancel.setOnClickListener {
        dialog.dismiss()
    }

    binding.btCreateNoticeComplete.setOnClickListener {
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

fun createDeleteNoticeDialog(
    context: Context,
    noticeViewModel: NoticeViewModel,
) {
    val binding by lazy {
        DialogDeleteNoticeBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    dialog = Dialog(context).apply {
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
    }

    binding.tvDeleteNoticeCancel.setOnClickListener {
        dialog.dismiss()
    }
}
