package com.example.dmsport_android.feature.notice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListNoticeBinding
import com.example.dmsport_android.feature.notice.model.Notice

class NoticeAdapter(
    private val noticeList : ArrayList<Notice>,
) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>(){

    class NoticeViewHolder(val binding : ListNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noticeList : Notice){
            binding.model = noticeList
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoticeViewHolder = NoticeViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_notice,
            parent,
            false,
        )
    )


    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(noticeList[position])
    }

    override fun getItemCount(): Int = noticeList.size
}