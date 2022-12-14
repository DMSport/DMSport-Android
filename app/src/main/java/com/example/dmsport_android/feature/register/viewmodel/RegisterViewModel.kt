package com.example.dmsport_android.feature.register.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.register.model.DuplicateRequest
import com.example.dmsport_android.feature.register.model.RegisterRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyEmailRequest
import com.example.dmsport_android.feature.verifyuser.model.VerifyRequest
import com.example.dmsport_android.feature.register.repository.RegisterRepository
import com.example.dmsport_android.feature.register.model.RegisterResponse
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.putPref
import com.example.dmsport_android.util.registerVisible
import com.example.dmsport_android.util.registerVisibleRe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(
    private val registerRepository: RegisterRepository,
    private val pref: SharedPreferences,
) : ViewModel() {

    private val _duplicateResponse = MutableLiveData<Response<Void>>()
    val duplicateResponse: LiveData<Response<Void>> = _duplicateResponse

    private val _verifyEmailResponse = MutableLiveData<Response<Void>>()
    val verifyEmailResponse: LiveData<Response<Void>> = _verifyEmailResponse

    private val _verifyResponse = MutableLiveData<Response<Void>>()
    val verifyResponse: LiveData<Response<Void>> = _verifyResponse

    private val _registerResponse = MutableLiveData<Response<RegisterResponse>>()
    val registerResponse: LiveData<Response<RegisterResponse>> = _registerResponse


    fun emailDuplicate(
        email: String,
    ) {
        val duplicateRequest = DuplicateRequest(email)
        viewModelScope.launch(Dispatchers.IO) {
            _duplicateResponse.postValue(registerRepository.emailDuplicate(duplicateRequest))
        }
    }

    fun sendVerifyEmail(
        email: String,
    ) {
        val verifyEmailRequest = VerifyEmailRequest(email)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyEmailResponse.postValue(registerRepository.sendVerifyEmail(verifyEmailRequest))
        }
    }

    fun verifyEmail(
        email : String,
        code : String,
    ) {
        val verifyRequest = VerifyRequest(email, code)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyResponse.postValue(registerRepository.verifyEmail(verifyRequest))
        }
    }

    fun register(
        password: String,
        name: String,
        email: String,
    ) {
        val registerRequest = RegisterRequest(
            password = password,
            name = name,
            email = email,
        )
        viewModelScope.launch(Dispatchers.IO) {
            _registerResponse.postValue(registerRepository.register(registerRequest))
        }
    }

    fun initVisible(){
        putPref(pref.edit(), registerVisible, false)
        putPref(pref.edit(), registerVisibleRe, false)
    }

    fun visible() : Boolean =
        if(getPref(pref, registerVisible, false) as Boolean){
            putPref(pref.edit(), registerVisible, false)
            true

        }else{
            putPref(pref.edit(), registerVisible, true)
            false
        }


    fun visibleRe() : Boolean =
        if(getPref(pref, registerVisibleRe, false) as Boolean){
            putPref(pref.edit(), registerVisibleRe, false)
            true

        }else{
            putPref(pref.edit(), registerVisibleRe, true)
            false
        }
}