package com.example.dmsport_android.feature.notice.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.notice.model.*
import com.example.dmsport_android.feature.vote.repository.NoticeRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.isAllEventNotice
import com.example.dmsport_android.util.isManaged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NoticeViewModel(
    private val noticeRepository: NoticeRepository,
    private val pref: SharedPreferences,
) : ViewModel() {

    private val _getNoticeResponse: MutableLiveData<Response<NoticeListResponse>> by lazy {
        MutableLiveData()
    }

    val getNoticeResponse: LiveData<Response<NoticeListResponse>> by lazy {
        _getNoticeResponse
    }

    private val _detailNoticeResponse: MutableLiveData<Response<DetailNoticeResponse>> by lazy {
        MutableLiveData()
    }

    val detailNoticeResponse: LiveData<Response<DetailNoticeResponse>> by lazy {
        _detailNoticeResponse
    }

    private val _createNoticeResponse: MutableLiveData<Response<Void>> by lazy {
        MutableLiveData()
    }

    val createNoticeResponse: LiveData<Response<Void>> by lazy {
        _createNoticeResponse
    }


    fun getNoticeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _getNoticeResponse.postValue(noticeRepository.getAllNotice())
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
        title: String,
        content: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun checkUserAuth(): Boolean =
        getPref(
            preferences = pref,
            key = isManaged,
            value = false
        ) as Boolean

    fun setAllNoticeList(noticeList: ArrayList<NoticeList>): ArrayList<NoticeList> {
        val tempList = arrayListOf<NoticeList>()
        var count = 0
        for (i in 0.until(noticeList.size)) {
            if (noticeList[i].type == "ALL") {
                tempList.add(noticeList[i])
                if(isAllEventNotice) count++
            }
            if(count == 2) break
        }
        return tempList
    }

    fun setEventNoticeList(noticeList: ArrayList<NoticeList>): ArrayList<NoticeList> {
        val tempList = arrayListOf<NoticeList>()
        var count = 0
        for (i in 0.until(noticeList.size)) {
            if (noticeList[i].type != "ALL") {
                tempList.add(noticeList[i])
                if(!isAllEventNotice) count++
            }
            if(count == 2) break
        }
        return tempList
    }


    fun setNoticeTypeTrue() {
        isAllEventNotice = true
    }

    fun setNoticeTypeFalse() {
        isAllEventNotice = false
    }
}