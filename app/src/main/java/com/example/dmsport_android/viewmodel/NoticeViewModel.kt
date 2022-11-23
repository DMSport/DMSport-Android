package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.response.AllNoticeResponse
import com.example.dmsport_android.dto.response.RecentNoticeResponse
import com.example.dmsport_android.repository.NoticeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.crypto.spec.DESKeySpec

class NoticeViewModel(
    private val noticeRepository: NoticeRepository,
) : ViewModel() {

    private val _recentNoticeResponse: MutableLiveData<Response<RecentNoticeResponse>> =
        MutableLiveData()
    val recentNoticeResponse: LiveData<Response<RecentNoticeResponse>> = _recentNoticeResponse

    private val _allNoticeResponse: MutableLiveData<Response<AllNoticeResponse>> =
        MutableLiveData()
    val allNoticeResponse: LiveData<Response<AllNoticeResponse>> = _allNoticeResponse


    fun getAllNotice() {
        viewModelScope.launch(Dispatchers.IO) {
            _allNoticeResponse.postValue(noticeRepository.getAllNotice())
        }
    }

    fun recentNotice() {
        viewModelScope.launch(Dispatchers.IO) {
            _recentNoticeResponse.postValue(noticeRepository.getRecentNotice())
        }
    }
}