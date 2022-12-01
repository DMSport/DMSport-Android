package com.example.dmsport_android.feature.vote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ListPositionBinding
import com.example.dmsport_android.feature.vote.model.Position

class VotePositionAdapter(
    private val positionList: ArrayList<Position>,
) : RecyclerView.Adapter<VotePositionAdapter.VotePositionViewHolder>() {

    class VotePositionViewHolder(val binding: ListPositionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Position) {
            binding.model = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotePositionViewHolder {
        val binding = DataBindingUtil.inflate<ListPositionBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_notice,
            parent,
            false,
        )

        return VotePositionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VotePositionViewHolder, position: Int) {
        holder.bind(positionList[position])
    }

    override fun getItemCount(): Int =
        positionList.size
}