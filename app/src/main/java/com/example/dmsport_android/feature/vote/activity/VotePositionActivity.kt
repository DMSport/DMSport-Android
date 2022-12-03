package com.example.dmsport_android.feature.vote.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityVotePositionBinding
import com.example.dmsport_android.feature.vote.adapter.VotePositionAdapter
import com.example.dmsport_android.feature.vote.model.Position
import com.example.dmsport_android.feature.vote.repository.VoteListRepository
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel
import com.example.dmsport_android.feature.vote.viewmodel.factory.VoteListViewModelFactory
import com.example.dmsport_android.util.initPref

class VotePositionActivity : BaseActivity<ActivityVotePositionBinding>(
    R.layout.activity_vote_position
) {

    private val voteListRepository by lazy {
        VoteListRepository()
    }

    private val voteListViewModelFactory by lazy {
        VoteListViewModelFactory(
            voteRepository = voteListRepository,
            pref = initPref(this),
            context = this,
        )
    }

    private val voteListViewModel by lazy {
        ViewModelProvider(this, voteListViewModelFactory)[VoteListViewModel::class.java]
    }

    private val soccerPositionList by lazy {
        arrayListOf("C.F", "S.F", "L.W", "C.M", "R.W", "A.M", "D.M", "L.S.T", "R.S.T", "S.W", "G.K")
    }

    private val basketBallPositionList by lazy {
        arrayListOf("P.G", "S.G", "S.F", "P.F", "C")
    }

    private val volleyBallPositionList by lazy {
        arrayListOf("Right", "Left", "Center", "Libero")
    }

    private val positionList: ArrayList<Position> by lazy {
        ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        checkSelectedEvent()
        initBackButton()
    }

    private fun initBackButton() {
        binding.imgVoteBack.setOnClickListener {
            finish()
        }
    }

    private fun checkSelectedEvent() {
        when (voteListViewModel.getSelectedNumber()) {
            1 -> setPositionList(soccerPositionList)
            2 -> setPositionList(basketBallPositionList)
            3 -> setPositionList(volleyBallPositionList)
        }
    }

    private fun setPositionList(arrayList: ArrayList<String>) {
        for (i in 0.until(arrayList.size)) {
            positionList.add(Position(arrayList[i]))
        }
    }

    private fun initRecyclerView() {
        binding.rvVoteSoccerList.run {
            adapter = VotePositionAdapter(
                positionList = positionList,
                activity = this@VotePositionActivity,
                voteListViewModel = voteListViewModel,
            )
            layoutManager = LinearLayoutManager(
                this@VotePositionActivity,
            )
        }
    }
}