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

    private val _recentNoticeListResponse: MutableLiveData<Response<RecentNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val recentNoticeResponse: LiveData<Response<RecentNoticeResponse>> by lazy {
        _recentNoticeListResponse
    }

    private val _noticeListResponse: MutableLiveData<Response<AllNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val allNoticeResponse: LiveData<Response<AllNoticeResponse>> by lazy {
        _noticeListResponse
    }

    private val _detailNoticeResponse: MutableLiveData<Response<DetailNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val detailNoticeResponse: LiveData<Response<DetailNoticeResponse>> by lazy {
        _detailNoticeResponse
    }


    fun getNoticeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _noticeListResponse.postValue(noticeRepository.getAllNotice())
        }
    }

    fun getRecentNoticeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _recentNoticeListResponse.postValue(noticeRepository.getRecentNotice())
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