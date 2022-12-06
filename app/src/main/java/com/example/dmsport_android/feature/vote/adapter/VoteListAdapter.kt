package com.example.dmsport_android.feature.vote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListVoteBinding
import com.example.dmsport_android.feature.vote.model.Vote
import com.example.dmsport_android.feature.vote.model.VoteListResponse
import com.example.dmsport_android.feature.vote.activity.VotePositionActivity
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel
import com.example.dmsport_android.util.*

/**
 * VoteList에 사용되는 Recyclerview Adapter 입니다.
 *
 * @param voteList 투표 결과 response
 * @param voteEventList vote 요소 목록
 * @param voteListViewModel vote 로직 처리를 위한 vm
 */
internal class VoteListAdapter(
    private val voteList: VoteListResponse?,
    private val voteEventList: ArrayList<Vote>?,
    private val voteListViewModel: VoteListViewModel,
    private val context: Context,
) : RecyclerView.Adapter<VoteListAdapter.VoteListViewHolder>() {

    class VoteListViewHolder(
        val binding: ListVoteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            voteList: VoteListResponse?,
            voteEventList: Vote?,
            voteListViewModel: VoteListViewModel,
        ) {
            binding.run {
                model = voteList
                vote = voteEventList
                viewModel = voteListViewModel
                util = ConvertTextUtil()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VoteListViewHolder =
        VoteListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_vote,
                parent,
                false,
            )
        )


    override fun onBindViewHolder(
        holder: VoteListViewHolder,
        position: Int,
    ) {
        holder.bind(
            voteList = voteList,
            voteEventList = voteEventList?.get(position),
            voteListViewModel = voteListViewModel,
        )

        if (voteEventList?.get(position)?.is_complete == true) {
            holder.binding.run {
                btVoteShowAllParticipants.run {
                    text = "팀 구성 보기"
                    setOnClickListener {
                        createParticipantsDialog(
                            context = context,
                            arrayList = voteEventList?.get(position)?.vote_user,
                            voteListViewModel = voteListViewModel,
                        )
                    }
                }
                btVoteApply.text = "마감"
            }
        } else {
            holder.binding.run {
                holder.binding.btVoteApply.text = voteListViewModel.isApplyed(
                    arrayList = voteEventList,
                    position = position,
                )
                btVoteApply.setOnClickListener {
                    holder.binding.btVoteApply.text = voteListViewModel.isApplyed(
                        arrayList = voteEventList,
                        position = position,
                    )
                    if (holder.binding.btVoteApply.text == "신청") {
                        val intent = Intent(context, VotePositionActivity::class.java)
                        intent.putExtra("voteId", voteEventList?.get(position)?.vote_id)
                        this@VoteListAdapter.context.startActivity(intent)
                    } else {
                        voteListViewModel.vote(voteEventList?.get(position)?.vote_id!!)
                    }
                }
                btVoteShowAllParticipants.run {
                    text = "신청자 목록 보기"
                    setOnClickListener {
                        createApplyersDialog(
                            context = context,
                            applyerList = voteEventList?.get(position)?.vote_user
                        )
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int =
        voteEventList?.size ?: 0
}
