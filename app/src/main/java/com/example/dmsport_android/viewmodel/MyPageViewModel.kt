package com.example.dmsport_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.response.MyPageResponse
import com.example.dmsport_android.repository.MyPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyPageViewModel(
    private val myPageRepository: MyPageRepository,
) : ViewModel() {

    private val _myPageResponse : MutableLiveData<Response<MyPageResponse>> = MutableLiveData()
    val myPageResponse : LiveData<Response<MyPageResponse>> = _myPageResponse

    private val _logoutResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val logoutResponse : LiveData<Response<Void>> = _logoutResponse

    fun my(){
        viewModelScope.launch(Dispatchers.IO){
            _myPageResponse.postValue(myPageRepository.my())
        }
    }

    fun logout(){
        viewModelScope.launch(Dispatchers.IO){
            _logoutResponse.postValue(myPageRepository.logout())
        }
    }

}