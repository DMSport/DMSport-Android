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

class VoteListAdapter(
    private val arrayList: ArrayList<VoteListResponse>,
    private val voteListViewModel : VoteListViewModel,
    ) : RecyclerView.Adapter<VoteListAdapter.VoteListViewHodler>(){

    class VoteListViewHodler(val binding : ListVoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(voteListResponse: VoteListResponse, vote : Vote, voteListViewModel : VoteListViewModel){
            binding.model = voteListResponse
            binding.vote = vote
            binding.viewModel = voteListViewModel
            binding.util = ConvertTextUtil()
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
        holder.bind(arrayList[position], arrayList[position].vote[position], voteListViewModel)
    }

    override fun getItemCount(): Int =
        arrayList.size
}