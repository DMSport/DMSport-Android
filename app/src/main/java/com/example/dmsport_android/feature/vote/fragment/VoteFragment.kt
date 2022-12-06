package com.example.dmsport_android.feature.vote.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentVoteBinding
import com.example.dmsport_android.feature.vote.adapter.VoteListAdapter
import com.example.dmsport_android.feature.vote.model.VoteListResponse
import com.example.dmsport_android.feature.vote.repository.VoteListRepository
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel
import com.example.dmsport_android.feature.vote.viewmodel.factory.VoteListViewModelFactory
import com.example.dmsport_android.util.*

class VoteFragment : BaseFragment<FragmentVoteBinding>(
    R.layout.fragment_vote,
) {

    private val voteListRepository: VoteListRepository by lazy {
        VoteListRepository()
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
        )[VoteListViewModel::class.java]
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
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState,
        )
        binding.viewModel = voteListViewModel
        isResume = false
        observeVoteListResponse()
        observeSelectedVote()
        initSelectedVote()
    }

    private fun observeVoteListResponse() {
        voteListViewModel.voteListResponse.observe(
            viewLifecycleOwner,
        ) {
            when (it.code()) {
                OK -> initRecyclerView(it.body()!!)
            }
        }
    }

    private fun initRecyclerView(
        voteListResponse: VoteListResponse,
    ) {
        binding.rvVoteList.run {
            adapter = VoteListAdapter(
                voteList = voteListResponse,
                voteEventList = voteListResponse.vote_list,
                voteListViewModel = voteListViewModel,
                context = requireActivity(),
            )
            layoutManager = LinearLayoutManager(
                this@VoteFragment.requireContext(),
            )
        }

        binding.rvVoteList.layoutManager =
            LinearLayoutManager(this.requireContext())
    }

    private fun observeSelectedVote() {
        voteListViewModel.run {
            selectedVote.observe(viewLifecycleOwner) {
                setBackgroundOff()
                setBackgroundOn(voteViewList[it])
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeSelectedVote()
        voteListViewModel.setSelectedVote()
    }

    private fun initSelectedVote() {
        setBackgroundOff()
        voteListViewModel.run {
            selectVote(initSelectedVote())
        }
        setBackgroundOn(
            voteViewList[voteListViewModel.initSelectedVote()],
        )
    }

    private fun setBackgroundOff() {
        for (i in 0..3)
            voteViewList[i].setBackgroundResource(
                R.drawable.vote_card_view_off,
            )
    }

    private fun setBackgroundOn(view: View) =
        view.setBackgroundResource(
            R.drawable.vote_card_view_on,
        )
}
