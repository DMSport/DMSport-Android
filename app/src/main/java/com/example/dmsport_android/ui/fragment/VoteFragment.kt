package com.example.dmsport_android.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.adapter.VoteListAdapter
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentVoteBinding
import com.example.dmsport_android.dto.response.Vote
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.repository.VoteListRepository
import com.example.dmsport_android.util.OK
import com.example.dmsport_android.util.initPref
import com.example.dmsport_android.viewmodel.VoteListViewModel
import com.example.dmsport_android.viewmodel.factory.VoteListViewModelFactory

class VoteFragment : BaseFragment<FragmentVoteBinding>(
    R.layout.fragment_vote,
) {

    private val voteListRepository: VoteListRepository by lazy {
        VoteListRepository()
    }

    private val pref: SharedPreferences by lazy {
        initPref(
            context = this.requireContext(),
        )
    }

    private val voteListViewModelFactory: VoteListViewModelFactory by lazy {
        VoteListViewModelFactory(
            voteRepository = voteListRepository,
            pref = pref,
        )
    }

    private val voteListViewModel: VoteListViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = voteListViewModelFactory,
        ).get(VoteListViewModel::class.java)
    }

    private val voteViewList: ArrayList<View> by lazy {
        arrayListOf(
            binding.cvVoteBad,
            binding.cvVoteSoc,
            binding.cvVoteBas,
            binding.cvVoteVol,
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = voteListViewModel
        observeVoteListResponse()
        observeSelectedVote()
        initSelectedVote()
    }

    private fun observeVoteListResponse() {
        voteListViewModel.voteListResponse.observe(
            viewLifecycleOwner,
        ) {
            when (it.code()) {
                OK -> initRecyclerView(
                    voteListResponse = it.body()!!
                )
            }
        }
    }

    private fun initRecyclerView(
        voteListResponse: VoteListResponse,
    ) {
        binding.rvVoteList.run {
            adapter = VoteListAdapter(
                voteList = voteListResponse,
                voteEventList = voteListResponse.vote,
                voteListViewModel = voteListViewModel,
            )
            layoutManager = LinearLayoutManager(
                this@VoteFragment.requireContext(),
            )
        }

        binding.rvVoteList.layoutManager =
            LinearLayoutManager(
                this.requireContext(),
            )
    }

    private fun observeSelectedVote() {
        voteListViewModel.run {
            selectedVote.observe(viewLifecycleOwner) {
                setBackgroundOff()
                setBackgroundOn(
                    view = voteViewList[it],
                )
            }
        }
    }

    private fun initSelectedVote() {
        setBackgroundOff()
        val number = voteListViewModel.initSelectedVote()
        voteListViewModel.selectVote(number)
        setBackgroundOn(
            view = voteViewList[number],
        )
    }

    fun setBackgroundOff() {
        for (i in 0..3)
            voteViewList[i].setBackgroundResource(
                R.drawable.vote_card_view_off,
            )
    }

    fun setBackgroundOn(view: View) =
        view.setBackgroundResource(
            R.drawable.vote_card_view_on,
        )
}
