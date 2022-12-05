package com.example.dmsport_android.feature.vote.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.vote.model.User
import com.example.dmsport_android.feature.vote.model.Vote
import com.example.dmsport_android.feature.vote.model.VoteListResponse
import com.example.dmsport_android.feature.vote.repository.VoteListRepository
import com.example.dmsport_android.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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
                        type = type
                    )
            )
        }
    }

    fun setSelectedVote() {
        selectVote(
            number = getPref(
                preferences = pref,
                key = selectedVoteNumber,
                value = 0
            ) as Int
        )
    }

    fun vote(
        voteId: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            voteListRepository.vote(voteId.toLong()).code().toString()
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
        _selectedVote.postValue(-1)
        _selectedVote.postValue(number)
        putPref(
            editor = pref.edit(),
            key = selectedVoteNumber,
            value = number
        )
        _currentVote.postValue(typeListTitle[number])
        getVoteList(typeList[number])
        putPref(
            editor = pref.edit(),
            key = selectedVoteNumber,
            value = number,
        )
    }

    fun getSelectedNumber(): Int =
        getPref(
            preferences = pref,
            key = selectedVoteNumber,
            0,
        ) as Int

    fun getOnClikedApply(): Boolean =
        getPref(
            preferences = pref,
            key = isApplyed,
            value = false,
        ) as Boolean

    private fun getUserName(): String =
        getPref(
            preferences = pref,
            key = "userName",
            value = ""
        ).toString()

    fun isApplyed(
        arrayList: ArrayList<Vote>?,
        position: Int,
    ): String {
        var text = "신청"
        Log.d("TEST", getUserName())
        Log.d("TEST", arrayList?.get(position)?.vote_user?.toString().toString())
        for (i in 0.until(arrayList?.get(position)?.vote_user?.size ?: 0)) {
            if (arrayList?.get(position)?.vote_user?.get(i)?.name?.trim().equals(getUserName())) {
                text = "취소"
                break
            }
        }
        return text
    }

    fun setFirstList(paticipantsList: ArrayList<User>?): ArrayList<String> {
        val arrayList = arrayListOf<String>()
        for (i in 0.until(paticipantsList?.size ?: 0)) {
            if ((paticipantsList?.get(i)?.team?.toInt() ?: 0) == 0) {
                arrayList.add(paticipantsList?.get(i)?.name ?: "")
            }
        }
        return arrayList
    }

    fun setSecondList(participantsList: ArrayList<User>?): ArrayList<String> {
        val arrayList = arrayListOf<String>()
        for (i in 0.until(participantsList?.size ?: 0)) {
            if ((participantsList?.get(i)?.team?.toInt() ?: 0) == 1) {
                arrayList.add(participantsList?.get(i)?.name ?: "")
            }
        }
        return arrayList
    }

}