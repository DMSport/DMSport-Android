package com.example.dmsport_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListVoteBinding
import com.example.dmsport_android.dto.response.Vote
import com.example.dmsport_android.dto.response.VoteListResponse

class VoteListAdapter(
    val arrayList: ArrayList<VoteListResponse>,
    ) : RecyclerView.Adapter<VoteListAdapter.VoteListViewHodler>(){

    class VoteListViewHodler(val binding : ListVoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(voteListResponse: VoteListResponse, vote : Vote){
            binding.main = voteListResponse
            binding.vote = vote
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteListViewHodler =
        VoteListViewHodler(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.list_vote,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: VoteListViewHodler, position: Int) {
        holder.bind(arrayList[position], arrayList[position].vote[position])
    }

    override fun getItemCount(): Int =
        arrayList.size
}