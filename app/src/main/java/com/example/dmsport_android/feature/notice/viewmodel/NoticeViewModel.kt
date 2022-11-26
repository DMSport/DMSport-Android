package com.example.dmsport_android.feature.notice.viewmodel

import android.content.MutableContextWrapper
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.notice.model.AllNoticeResponse
import com.example.dmsport_android.feature.notice.model.CreateNoticeRequest
import com.example.dmsport_android.feature.notice.model.DetailNoticeResponse
import com.example.dmsport_android.feature.notice.model.RecentNoticeResponse
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.isManaged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NoticeViewModel(
    private val noticeRepository: NoticeRepository,
    private val pref : SharedPreferences,
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

    private val _createNoticeResponse : MutableLiveData<Response<Void>> by lazy {
        MutableLiveData()
    }

    val createNoticeResponse : LiveData<Response<Void>> by lazy {
        _createNoticeResponse
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

    fun createNotice(
        title : String,
        content : String,
    ){
        viewModelScope.launch(Dispatchers.IO){
            _createNoticeResponse.postValue(
                noticeRepository.createNotice(
                    CreateNoticeRequest(
                        title = title,
                        content = content,
                    )
                )
            )
        }
    }

    fun checkUserAuth() : Boolean =
        getPref(
            preferences = pref,
            key = isManaged,
            value = false
        ) as Boolean
}