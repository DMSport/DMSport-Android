package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.R
import com.example.dmsport_android.dto.request.DuplicateRequest
import com.example.dmsport_android.dto.request.RegisterRequest
import com.example.dmsport_android.dto.request.VerifyEmailRequest
import com.example.dmsport_android.dto.request.VerifyRequest
import com.example.dmsport_android.dto.response.RegisterResponse
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(
    private val registerRepository: RegisterRepository,
    private val pref: SharedPreferences
) : ViewModel() {

    private val _duplicateResponse = MutableLiveData<Response<Void>>()
    val duplicateResponse: LiveData<Response<Void>> = _duplicateResponse

    private val _verifyEmailResponse = MutableLiveData<Response<Void>>()
    val verifyEmailResponse: LiveData<Response<Void>> = _verifyEmailResponse

    private val _verifyResponse = MutableLiveData<Response<Void>>()
    val verifyResponse: LiveData<Response<Void>> = _verifyResponse

    private val _registerResponse = MutableLiveData<Response<RegisterResponse>>()
    val registerResponse: LiveData<Response<RegisterResponse>> = _registerResponse

    private val _inputType = MutableLiveData<Int>()
    val inputType: LiveData<Int> = _inputType

    private val _toggle = MutableLiveData<Int>()
    val toggle: LiveData<Int> = _toggle

    private val _inputTypeRe = MutableLiveData<Int>()
    val inputTypeRe: LiveData<Int> = _inputTypeRe

    private val _toggleRe = MutableLiveData<Int>()
    val toggleRe: LiveData<Int> = _toggleRe

    fun duplicate(email: String) {
        val duplicateRequest = DuplicateRequest(email)
        viewModelScope.launch(Dispatchers.IO) {
            _duplicateResponse.postValue(registerRepository.duplicate(duplicateRequest))
        }
    }

    fun verifyEmail(email: String) {
        val verifyEmailRequest = VerifyEmailRequest(email)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyEmailResponse.postValue(registerRepository.verifyEmail(verifyEmailRequest))
        }
    }

    fun verify(code: String, email: String) {
        val verifyRequest = VerifyRequest(code, email)
        viewModelScope.launch(Dispatchers.IO) {
            _verifyResponse.postValue(registerRepository.verify(verifyRequest))
        }
    }

    fun register(password: String, name: String, email: String) {
        val registerRequest = RegisterRequest(password, name, email)
        viewModelScope.launch(Dispatchers.IO) {
            _registerResponse.postValue(registerRepository.register(registerRequest))
        }
    }

    fun initVisible(){
        putPref(pref.edit(), "visiblePw", false)
        _toggle.value = R.drawable.ic_visible_on
        putPref(pref.edit(), "visiblePwRe", false)
        _toggleRe.value = R.drawable.ic_visible_off
    }

    fun pwVisible() {
        if (getPref(pref, "visiblePw", false) as Boolean) {
            _toggle.value = R.drawable.ic_visible_on
            _inputType.value = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "visiblePw", false)
        } else {
            _toggle.value = R.drawable.ic_visible_off
            _inputType.value = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "visiblePw", true)
        }
    }

    fun pwReVisible() {
        if (getPref(pref, "visiblePwRe", false) as Boolean) {
            _toggleRe.value = R.drawable.ic_visible_on
            _inputTypeRe.value = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "visiblePwRe", false)
        } else {
            _toggleRe.value = R.drawable.ic_visible_off
            _inputTypeRe.value = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "visiblePwRe", true)
        }
    }
}