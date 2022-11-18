package com.example.dmsport_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListVoteBinding
import com.example.dmsport_android.dto.response.Vote
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.util.ConvertTextUtil
import com.example.dmsport_android.viewmodel.VoteListViewModel

/**
 * VoteList에 사용되는 Recyclerview Adapter 입니다.
 *
 * @param voteList 투표 결과 response
 * @param voteEventList vote 요소 목록
 * @param voteListViewModel vote 로직 처리를 위한 vm
 */
internal class VoteListAdapter(
    private val voteList: VoteListResponse,
    private val voteEventList: ArrayList<Vote>,
    private val voteListViewModel: VoteListViewModel,
) : RecyclerView.Adapter<VoteListAdapter.VoteListViewHolder>() {

    class VoteListViewHolder(
        val binding: ListVoteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            voteList: VoteListResponse,
            voteEventList: Vote,
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
            voteEventList = voteEventList[position],
            voteListViewModel = voteListViewModel,
        )
    }

    override fun getItemCount(): Int =
        voteEventList.size
}
