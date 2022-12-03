package com.example.dmsport_android.feature.vote.viewmodel.factory

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.vote.repository.VoteListRepository
import com.example.dmsport_android.feature.vote.viewmodel.VoteListViewModel

class VoteListViewModelFactory(
    private val voteRepository: VoteListRepository,
    private val pref : SharedPreferences,
    private val context : Context,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        VoteListViewModel(voteRepository, pref) as T

}