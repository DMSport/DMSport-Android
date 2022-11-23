package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.request.ChagePasswordRequest
import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.repository.ChangePasswordRepository
import com.example.dmsport_android.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class EmailChangePwViewModel(
    private val changePasswordRepository: ChangePasswordRepository,
    private val preferences: SharedPreferences,
) : ViewModel() {

    private val _findVerifyEmailResponse = MutableLiveData<Response<Void>>()
    val findVerifyEmailResponse: LiveData<Response<Void>> = _findVerifyEmailResponse

    private val _emailChangePwResponse = MutableLiveData<Response<Void>>()
    val emailChangePwResponse: LiveData<Response<Void>> = _emailChangePwResponse

    private val _verifyResponse = MutableLiveData<Response<Void>>()
    val verifyResponse: LiveData<Response<Void>> = _verifyResponse

    private val _changePasswordResponse = MutableLiveData<Response<Void>>()
    val changePasswordResponse : LiveData<Response<Void>> = _changePasswordResponse

    fun changePassword(
        old_password : String,
        new_password : String,
    ){
        viewModelScope.launch(Dispatchers.IO){
            _changePasswordResponse.postValue(
                changePasswordRepository.changePassword(
                    ChagePasswordRequest(
                        old_password = old_password,
                        new_password = new_password,
                    )
                )
            )
        }
    }

    fun emailChangePw(
        email: String,
        new_password: String,
    ) {
        val emailChangePwRequest = EmailChangePwRequest(email, new_password)
        viewModelScope.launch(Dispatchers.IO) {
            _emailChangePwResponse.postValue(
                changePasswordRepository.emailChangePw(
                    emailChangePwRequest
                )
            )
        }
    }

    fun verify(
        email: String,
        auth_code: String,
    ) {
        val verifyRequest = VerifyRequest(email, auth_code)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyResponse.postValue(
                changePasswordRepository.verify(
                    verifyRequest
                )
            )
        }
    }

    fun findVerifyEmail(
        email: String,
    ) {
        val findPwVerifyEmailRequest = FindPwVerifyEmailRequest(email)
        viewModelScope.launch(Dispatchers.IO) {
            _findVerifyEmailResponse.postValue(
                changePasswordRepository.findVerifyEmail(
                    findPwVerifyEmailRequest
                )
            )
        }
    }

    fun initVisible(){
        putPref(preferences.edit(), registerVisible, false)
        putPref(preferences.edit(), registerVisibleRe, false)
    }

    fun visible() : Boolean =
        if(getPref(preferences, registerVisible, false) as Boolean){
            putPref(preferences.edit(), registerVisible, false)
            true

        }else{
            putPref(preferences.edit(), registerVisible, true)
            false
        }


    fun visibleRe() : Boolean =
        if(getPref(preferences, registerVisibleRe, false) as Boolean){
            putPref(preferences.edit(), registerVisibleRe, false)
            true

        }else{
            putPref(preferences.edit(), registerVisibleRe, true)
            false
        }
}