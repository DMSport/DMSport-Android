package com.example.dmsport_android.feature.mypage.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.deleteuser.DeleteUserRequest
import com.example.dmsport_android.feature.mypage.repository.MyPageRepository
import com.example.dmsport_android.feature.mypage.model.MyPageResponse
import com.example.dmsport_android.util.isManaged
import com.example.dmsport_android.util.isVerified
import com.example.dmsport_android.util.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyPageViewModel(
    private val myPageRepository: MyPageRepository,
    private val editor : SharedPreferences.Editor,
) : ViewModel() {

    private val _myPageResponse : MutableLiveData<Response<MyPageResponse>> = MutableLiveData()
    val myPageResponse : LiveData<Response<MyPageResponse>> = _myPageResponse

    private val _logoutResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val logoutResponse : LiveData<Response<Void>> = _logoutResponse

    private val _deleteUserResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val deleteUserResponse : LiveData<Response<Void>> = _deleteUserResponse

    fun initializeVerified(){
        isVerified = !isVerified
    }

    fun fetchMyPage(){
        viewModelScope.launch(Dispatchers.IO){
            _myPageResponse.postValue(myPageRepository.fetchMyPage())
        }
    }

    fun logout(){
        viewModelScope.launch(Dispatchers.IO){
            _logoutResponse.postValue(myPageRepository.userLogout())
        }
    }

    fun deleteUser(deleteUserRequest: DeleteUserRequest){
        viewModelScope.launch(Dispatchers.IO){
            _deleteUserResponse.postValue(myPageRepository.deleteUser(deleteUserRequest))
        }
    }

    fun saveUserAuth(auth : String?){
        putPref(
            editor = editor,
            key = isManaged,
            value = auth?.split('_')?.get(1)?.equals("MANAGER") ?: "USER"
        )
    }

}