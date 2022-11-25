package com.example.dmsport_android.feature.notice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListAdminNoticeBinding
import com.example.dmsport_android.feature.notice.model.Admin

class AdminNoticeAdapter(
    private val adminNoticeList : ArrayList<Admin>,
) : RecyclerView.Adapter<AdminNoticeAdapter.NoticeViewHolder>(){

    class NoticeViewHolder(val binding : ListAdminNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(adminNoticeList : Admin){
            binding.model = adminNoticeList
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoticeViewHolder = NoticeViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_admin_notice,
            parent,
            false,
        )
    )


    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(adminNoticeList[position])
    }

    override fun getItemCount(): Int = adminNoticeList.size
}