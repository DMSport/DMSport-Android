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

    private val _selectedNumber : MutableLiveData<Int> = MutableLiveData()
    val selectedNumber : LiveData<Int> = _selectedNumber

    private val today = LocalDate.now()

    fun getVoteList(
        type : String,
    ){
        viewModelScope.launch(Dispatchers.IO){
             _voteListResponse.postValue(voteListRepository.getVoteList(type, today.toString()))
        }
    }

    val initSelectedVote : () -> Unit = { putPref(pref.edit(), selectedVote, 1); _selectedNumber.value = 1}

    fun selectVote(number : Int) {
        putPref(pref.edit(), selectedVote, number)
        _selectedNumber.value = number
        when(number){
            1->getVoteList(BADMINTON)
            2->getVoteList(SOCCER)
            3->getVoteList(BASKETBALL)
            4->getVoteList(VOLLEYBALL)
        }

    }
}