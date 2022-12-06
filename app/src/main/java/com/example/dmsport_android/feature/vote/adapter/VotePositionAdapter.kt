package com.example.dmsport_android.feature.vote.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListPositionBinding
import com.example.dmsport_android.feature.vote.model.Position
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel

class VotePositionAdapter(
    private val positionList: ArrayList<Position>,
    private val activity : Activity,
    private val voteId : Int,
    private val voteListViewModel : VoteListViewModel,
) : RecyclerView.Adapter<VotePositionAdapter.VotePositionViewHolder>() {

    class VotePositionViewHolder(val binding: ListPositionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Position) {
            binding.model = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotePositionViewHolder =
        VotePositionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_position,
                parent,
                false,
            )
        )


    override fun onBindViewHolder(holder: VotePositionViewHolder, position: Int) {
        holder.bind(positionList[position])
        holder.binding.btPositionApply.setOnClickListener {
            voteListViewModel.vote(voteId)
            activity.finish()
        }
    }

    override fun getItemCount(): Int =
        positionList.size
}