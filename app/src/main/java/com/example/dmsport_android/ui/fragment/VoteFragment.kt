package com.example.dmsport_android.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import kotlin.math.max

class VoteFragment : BaseFragment<FragmentVoteBinding>(R.layout.fragment_vote) {

    private val voteListRepository: VoteListRepository by lazy {
        VoteListRepository()
    }

    private val pref: SharedPreferences by lazy {
        initPref(this.requireContext())
    }

    private val voteListViewModelFactory: VoteListViewModelFactory by lazy {
        VoteListViewModelFactory(voteListRepository, pref)
    }

    private val voteListViewModel: VoteListViewModel by lazy {
        ViewModelProvider(this, voteListViewModelFactory).get(VoteListViewModel::class.java)
    }

    private val arrayList: ArrayList<VoteListResponse> by lazy {
        ArrayList()
    }

    private val voteViewList : ArrayList<View> by lazy {
        arrayListOf(
            binding.cvVoteBad,
            binding.cvVoteSoc,
            binding.cvVoteBas,
            binding.cvVoteVol,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = voteListViewModel
        observeVoteListResponse()
        observeSelectedVote()
        initSelectedVote()
    }

    private fun observeVoteListResponse() {
        voteListViewModel.voteListResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                OK->{
                    arrayList.run {
                        clear()
                        add(it.body()!!)
                        initRecyclerView()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvVoteList.adapter = VoteListAdapter(arrayList, voteListViewModel)
        binding.rvVoteList.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun observeSelectedVote(){
        voteListViewModel.run {
            selectedVote.observe(viewLifecycleOwner, Observer {
                setBackgroundOff()
                setBackgroundOn(voteViewList[it])
            })
        }
    }

    private fun initSelectedVote(){
        setBackgroundOff()
        val number = voteListViewModel.initSelectedVote()
        voteListViewModel.selectVote(number)
        setBackgroundOn(voteViewList[number])
    }

    fun setBackgroundOff() {
        for (i in 0..3) voteViewList[i].setBackgroundResource(R.drawable.vote_card_view_off)
    }

    fun setBackgroundOn(view: View) =
        view.setBackgroundResource(R.drawable.vote_card_view_on)
}
