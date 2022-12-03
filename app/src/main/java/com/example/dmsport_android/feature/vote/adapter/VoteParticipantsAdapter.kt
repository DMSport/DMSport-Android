package com.example.dmsport_android.feature.vote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListParticipantsBinding

class VoteParticipantsAdapter(
    private val participantsList: ArrayList<String>
) : RecyclerView.Adapter<VoteParticipantsAdapter.VoteParticipantsViewHolder>() {

    class VoteParticipantsViewHolder(val binding: ListParticipantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(participants : String){
                binding.name = participants
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteParticipantsViewHolder =
        VoteParticipantsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_participants,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VoteParticipantsViewHolder, position: Int) {
        holder.bind(participantsList[position])
    }

    override fun getItemCount(): Int =
        participantsList.size
}