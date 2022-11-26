package com.example.dmsport_android.feature.notice.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListNoticeBinding
import com.example.dmsport_android.feature.notice.activity.DetailNoticeActivity
import com.example.dmsport_android.feature.notice.model.NoticeList
import com.example.dmsport_android.feature.notice.viewmodel.NoticeViewModel
import com.example.dmsport_android.util.noticeId
import com.example.dmsport_android.util.putPref
import com.example.dmsport_android.util.startIntentWithFlag
import java.util.*
/**
 * AllNoticeList에 사용되는 Recyclerview Adapter 입니다.
 *
 * @param noticeList 전체 공지 사항 response
 * @param context intent, sharedpreferences 로직 처리를 위한 context
 * @param editor sharedpreferences 저장을 위한 sharedpreferences editor
 */
class NoticeAdapter(
    private val noticeList: ArrayList<NoticeList>,
    private val context : Context,
    private val editor : SharedPreferences.Editor,
    private val noticeViewModel : NoticeViewModel,
) : RecyclerView.Adapter<NoticeAdapter.AllNoticeViewHolder>() {

    class AllNoticeViewHolder(val binding: ListNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            allNoticeList: NoticeList,
        ) {
            binding.model = allNoticeList
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AllNoticeViewHolder =
        AllNoticeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_notice,
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: AllNoticeViewHolder,
        position: Int,
    ) {
        holder.bind(
            allNoticeList = noticeList[position],
        )
        holder.itemView.setOnClickListener {
            putPref(
                editor = editor,
                key = noticeId,
                value = noticeList[position].id,
            )
            startIntentWithFlag(
                context = context,
                activity = DetailNoticeActivity::class.java,
            )
        }
    }

    override fun getItemCount(): Int =
        noticeList.size
}