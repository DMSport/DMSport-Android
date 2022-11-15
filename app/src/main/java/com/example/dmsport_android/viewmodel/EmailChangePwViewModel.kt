package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.dto.request.EmailChangePwRequest
import com.example.dmsport_android.dto.request.FindPwVerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.repository.EmailChangePwRepository
import com.example.dmsport_android.util.emailChangePwVisible
import com.example.dmsport_android.util.emailChangePwVisibleRe
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class EmailChangePwViewModel(
    private val emailChangePwRepository: EmailChangePwRepository,
    private val preferences: SharedPreferences,
) : ViewModel() {

    private val _findVerifyEmailResponse = MutableLiveData<Response<Void>>()
    val findVerifyEmailResponse: LiveData<Response<Void>> = _findVerifyEmailResponse

    private val _emailChangePwResponse = MutableLiveData<Response<Void>>()
    val emailChangePwResponse: LiveData<Response<Void>> = _emailChangePwResponse

    private val _verifyResponse = MutableLiveData<Response<Void>>()
    val verifyResponse: LiveData<Response<Void>> = _verifyResponse


    fun emailChangePw(email: String, new_password: String) {
        val emailChangePwRequest = EmailChangePwRequest(email, new_password)
        viewModelScope.launch(Dispatchers.IO) {
            _emailChangePwResponse.postValue(
                emailChangePwRepository.emailChangePw(
                    emailChangePwRequest
                )
            )
        }
    }

    fun verify(auth_code: String, email: String) {
        val verifyRequest = VerifyRequest(auth_code, email)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyResponse.postValue(
                emailChangePwRepository.verify(
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
                emailChangePwRepository.findVerifyEmail(
                    findPwVerifyEmailRequest
                )
            )
        }
    }

    fun initVisible() {
        putPref(preferences.edit(), emailChangePwVisible, false)
    }

    fun visible(): Boolean {
        if (getPref(preferences, emailChangePwVisible, false) as Boolean) {
            putPref(preferences.edit(), emailChangePwVisible, false)
            return true
        } else {
            putPref(preferences.edit(), emailChangePwVisible, true)
            return false
        }
    }

    fun visibleRe(): Boolean {
        if (getPref(preferences, emailChangePwVisibleRe, false) as Boolean) {
            putPref(preferences.edit(), emailChangePwVisibleRe, false)
            return true

        } else {
            putPref(preferences.edit(), emailChangePwVisibleRe, true)
            return false
        }
    }

}