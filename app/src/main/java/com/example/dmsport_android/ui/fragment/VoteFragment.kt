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
import com.example.dmsport_android.util.initPref
import com.example.dmsport_android.viewmodel.VoteListViewModel
import com.example.dmsport_android.viewmodel.factory.VoteListViewModelFactory
import java.time.LocalDate

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
        voteListViewModel.initSelectedVote
    }

    private fun initRecyclerView() {
        binding.rvVoteList.adapter = VoteListAdapter(arrayList)
        binding.rvVoteList.layoutManager = LinearLayoutManager(this.requireContext())
    }

    private fun observeVoteListResponse() {
        voteListViewModel.voteListResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                OK->{
                    arrayList.clear()
                    arrayList.add(it.body()!!)
                    initRecyclerView()
                }
            }
        })
    }

    private fun observeSelectedVote(){
        voteListViewModel.selectedNumber.observe(viewLifecycleOwner, Observer {
            initSelected()
            when(it){
                1->setBackgroundOn(binding.cvVoteBad)
                2->setBackgroundOn(binding.cvVoteSoc)
                3->setBackgroundOn(binding.cvVoteBas)
                else -> setBackgroundOn(binding.cvVoteVol)
            }
        })
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
