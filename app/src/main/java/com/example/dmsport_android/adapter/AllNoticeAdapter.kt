package com.example.dmsport_android.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListAllNoticeMoreBinding
import com.example.dmsport_android.dto.response.AllNoticeList
import com.example.dmsport_android.ui.activity.DetailNoticeActivity
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.NoticeViewModel
import java.text.SimpleDateFormat
import java.util.*

class AllNoticeAdapter(
    private val arrayList: ArrayList<AllNoticeList>,
    private val noticeViewModel: NoticeViewModel,
    private val context : Context
) : RecyclerView.Adapter<AllNoticeAdapter.AllNoticeViewHolder>() {

    class AllNoticeViewHolder(val binding: ListAllNoticeMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(allNoticeList: AllNoticeList, noticeViewModel: NoticeViewModel) {
            binding.viewModel = noticeViewModel
            binding.allNoticeList = allNoticeList

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNoticeViewHolder =
        AllNoticeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_all_notice_more,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllNoticeViewHolder, position: Int) {
        holder.bind(arrayList[position], noticeViewModel)
        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    DetailNoticeActivity::class.java,
                ).addFlags(
                    FLAG_ACTIVITY_NEW_TASK
                )
            )
        }
    }

    override fun getItemCount(): Int =
        arrayList.size
}