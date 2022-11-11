package com.example.dmsport_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.response.VoteListResponse
import com.example.dmsport_android.repository.VoteListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class VoteListViewModel(
    private val voteListRepository : VoteListRepository,
) : ViewModel(){

    private val _voteListResponse : MutableLiveData<Response<VoteListResponse>> = MutableLiveData()
    val voteListResponse : LiveData<Response<VoteListResponse>> = _voteListResponse

    fun getVoteList(
        type : String,
        date : String,
    ){
        viewModelScope.launch(Dispatchers.IO){
             _voteListResponse.postValue(voteListRepository.getVoteList(type, date))
        }
    }
}