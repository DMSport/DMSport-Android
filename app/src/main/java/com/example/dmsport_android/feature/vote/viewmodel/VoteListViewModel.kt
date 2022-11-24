package com.example.dmsport_android.feature.vote.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.feature.vote.repository.VoteListRepository
import com.example.dmsport_android.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate

class VoteListViewModel(
    private val voteListRepository: VoteListRepository,
    private val pref: SharedPreferences,
) : ViewModel() {

    private val _voteListResponse: MutableLiveData<Response<VoteListResponse>> = MutableLiveData()
    val voteListResponse: LiveData<Response<VoteListResponse>> = _voteListResponse

    private val _selectedVote: MutableLiveData<Int> = MutableLiveData()
    val selectedVote: LiveData<Int> = _selectedVote

    private val _currentVote: MutableLiveData<String> = MutableLiveData()
    val currentVote: LiveData<String> = _currentVote

    private fun getVoteList(
        type: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _voteListResponse.postValue(
                voteListRepository
                    .getVoteList(
                        type = type,
                        date = LocalDate.now().toString(),
                    )
            )
        }
    }

    fun vote(
        voteId : Int,
    ){
        viewModelScope.launch(Dispatchers.IO){
            voteListRepository.vote(voteId.toLong())
            selectVote(
                number = getPref(
                    preferences = pref,
                    key = selectedVoteNumber,
                    value = 0
                ) as Int
            )
        }
    }

    fun initSelectedVote(): Int =
        getPref(
            preferences = pref,
            key = selectedVoteNumber,
            value = 0,
        ) as Int

    fun selectVote(number: Int) {
        _selectedVote.postValue(number)
        _currentVote.postValue(typeListTitle[number])
        getVoteList(typeList[number])
        putPref(
            editor = pref.edit(),
            key = selectedVoteNumber,
            value = number,
        )
    }
}