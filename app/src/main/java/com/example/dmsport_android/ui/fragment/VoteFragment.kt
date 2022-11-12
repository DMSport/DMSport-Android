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
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.repository.VoteListRepository
import com.example.dmsport_android.util.OK
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.initPref
import com.example.dmsport_android.util.selectedNumber
import com.example.dmsport_android.viewmodel.VoteListViewModel
import com.example.dmsport_android.viewmodel.factory.VoteListViewModelFactory

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = voteListViewModel
        observeVoteListResponse()
        observeSelectedVote()
        initSelectedVote()
    }

    private fun initRecyclerView() {
        binding.rvVoteList.adapter = VoteListAdapter(arrayList, voteListViewModel)
        binding.rvVoteList.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun initSelectedVote(){
        initSelected()
        when(voteListViewModel.initSelectedVote()){
            1->{
                setBackgroundOn(binding.cvVoteBad)
                voteListViewModel.selectVote(1)
            }
            2->{
                setBackgroundOn(binding.cvVoteBad)
                voteListViewModel.selectVote(2)
            }
            3->{
                setBackgroundOn(binding.cvVoteBad)
                voteListViewModel.selectVote(3)
            }
            else->{
                setBackgroundOn(binding.cvVoteBad)
                voteListViewModel.selectVote(4)
            }
        }
    }

    private fun observeVoteListResponse() {
        voteListViewModel.voteListResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                OK->{
                    arrayList.run {
                        clear()
                        add(it.body()!!)
                    }
                    initRecyclerView()
                }
            }
        })
    }

    private fun observeSelectedVote(){
        voteListViewModel.run {
            selectedVote.observe(viewLifecycleOwner, Observer {
                initSelected()
                when(it){
                    1->setBackgroundOn(binding.cvVoteBad)
                    2->setBackgroundOn(binding.cvVoteSoc)
                    3->setBackgroundOn(binding.cvVoteBas)
                    else -> setBackgroundOn(binding.cvVoteVol)
                }
            })
        }
    }

    fun initSelected() {
        setBackgroundOff(binding.cvVoteBad)
        setBackgroundOff(binding.cvVoteSoc)
        setBackgroundOff(binding.cvVoteVol)
        setBackgroundOff(binding.cvVoteBas)
    }

    fun setBackgroundOff(view: View) =
        view.setBackgroundResource(R.drawable.vote_card_view_off)

    fun setBackgroundOn(view: View) =
        view.setBackgroundResource(R.drawable.vote_card_view_on)
}
