package com.example.dmsport_android.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.VoteListRepository
import com.example.dmsport_android.viewmodel.VoteListViewModel

class VoteListViewModelFactory(
    private val voteRepository: VoteListRepository,
    private val pref : SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        VoteListViewModel(voteRepository, pref) as T

}