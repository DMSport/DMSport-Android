package com.example.dmsport_android.feature.notice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.notice.model.AllNoticeResponse
import com.example.dmsport_android.feature.notice.model.DetailNoticeResponse
import com.example.dmsport_android.feature.notice.model.RecentNoticeResponse
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NoticeViewModel(
    private val noticeRepository: NoticeRepository,
) : ViewModel() {

    private val _recentNoticeResponse: MutableLiveData<Response<RecentNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val recentNoticeResponse: LiveData<Response<RecentNoticeResponse>> by lazy {
        _recentNoticeResponse
    }

    private val _allNoticeResponse: MutableLiveData<Response<AllNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val allNoticeResponse: LiveData<Response<AllNoticeResponse>> by lazy {
        _allNoticeResponse
    }

    private val _detailNoticeResponse: MutableLiveData<Response<DetailNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val detailNoticeResponse: LiveData<Response<DetailNoticeResponse>> by lazy {
        _detailNoticeResponse
    }


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

    fun getDetailNotice(noticeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailNoticeResponse.postValue(
                noticeRepository.getDetailNotice(
                    noticeId = noticeId.toLong()
                )
            )
        }
    }
}