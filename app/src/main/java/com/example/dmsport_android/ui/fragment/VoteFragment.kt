package com.example.dmsport_android.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentVoteBinding

class VoteFragment : BaseFragment<FragmentVoteBinding>(R.layout.fragment_vote) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.voteFragment = this
    }

    fun initBadmintonSelected() {
        binding.cvVoteBad.setBackgroundResource(R.drawable.vote_card_view_on)
        binding.cvVoteSoc.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteVol.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteBas.setBackgroundResource(R.drawable.vote_card_view_off)
    }

    fun initSoccerSelected() {
        binding.cvVoteSoc.setBackgroundResource(R.drawable.vote_card_view_on)
        binding.cvVoteBas.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteBad.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteVol.setBackgroundResource(R.drawable.vote_card_view_off)
    }

    fun initBasSelected() {
        binding.cvVoteBas.setBackgroundResource(R.drawable.vote_card_view_on)
        binding.cvVoteBad.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteVol.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteSoc.setBackgroundResource(R.drawable.vote_card_view_off)
    }

    fun initVolSelected() {
        binding.cvVoteVol.setBackgroundResource(R.drawable.vote_card_view_on)
        binding.cvVoteSoc.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteBas.setBackgroundResource(R.drawable.vote_card_view_off)
        binding.cvVoteBad.setBackgroundResource(R.drawable.vote_card_view_off)
    }
}
