package com.example.dmsport_android.feature.vote.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityVotePositionBinding
import com.example.dmsport_android.feature.notice.adapter.NoticeAdapter
import com.example.dmsport_android.feature.notice.model.NoticeList
import com.example.dmsport_android.feature.vote.adapter.VotePositionAdapter
import com.example.dmsport_android.feature.vote.model.Position

class VotePositionActivity : BaseActivity<ActivityVotePositionBinding>(
    R.layout.activity_vote_position
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}