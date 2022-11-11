package com.example.dmsport_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.VoteListRepository
import com.example.dmsport_android.viewmodel.VoteListViewModel

class VoteListViewModelFactory(
    private val voteRepository: VoteListRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        VoteListViewModel(voteRepository) as T

}