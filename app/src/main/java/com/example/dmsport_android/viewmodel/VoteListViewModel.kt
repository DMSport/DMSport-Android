package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.repository.VoteListRepository
import com.example.dmsport_android.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate

class VoteListViewModel(
    private val voteListRepository : VoteListRepository,
    private val pref : SharedPreferences,
) : ViewModel(){

    private val _voteListResponse : MutableLiveData<Response<VoteListResponse>> = MutableLiveData()
    val voteListResponse : LiveData<Response<VoteListResponse>> = _voteListResponse

    private val _selectedVote : MutableLiveData<Int> = MutableLiveData()
    val selectedVote : LiveData<Int> = _selectedVote

    private val _currentVote : MutableLiveData<String> = MutableLiveData()
    val currentVote : LiveData<String> = _currentVote

    private fun getVoteList(
        type : String,
    ){
        viewModelScope.launch(Dispatchers.IO){
             _voteListResponse.postValue(voteListRepository.getVoteList(type, LocalDate.now().toString()))
        }
    }

    fun initSelectedVote() : Int =
        getPref(pref, selectedVoteNumber, 0) as Int

    fun selectVote(number : Int) {
        _selectedVote.value = number
        _currentVote.value = typeListTitle[number]
        getVoteList(typeList[number])
        putPref(pref.edit(), selectedVoteNumber, number)
    }
}