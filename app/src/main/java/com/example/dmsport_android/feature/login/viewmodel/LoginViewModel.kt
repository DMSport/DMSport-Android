package com.example.dmsport_android.feature.login.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.feature.login.repository.LoginRepository
import com.example.dmsport_android.feature.login.model.LoginRequest
import com.example.dmsport_android.feature.login.model.LoginResponse
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.loginVisible
import com.example.dmsport_android.util.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val pref: SharedPreferences,
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Response<LoginResponse>>()
    val loginResponse: LiveData<Response<LoginResponse>> = _loginResponse

    fun login(
        email: String,
        pw: String,
    ) {
        val loginRequest = LoginRequest(
            email = email,
            password = pw,
        )
        viewModelScope.launch(Dispatchers.IO) {
            _loginResponse.postValue(loginRepository.login(loginRequest))
        }
    }

    fun initVisible(){
        putPref(pref.edit(), loginVisible, false)
    }

    fun visible(): Boolean =
        if (getPref(pref, loginVisible, false) as Boolean) {
            putPref(pref.edit(), loginVisible, false)
            true
        } else {
            putPref(pref.edit(), loginVisible, true)
            false
        }
}