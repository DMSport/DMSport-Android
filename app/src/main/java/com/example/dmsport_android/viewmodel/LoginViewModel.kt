package com.example.dmsport_android.viewmodel

import android.content.SharedPreferences
import android.text.InputType
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmsport_android.R
import com.example.dmsport_android.dto.request.LoginRequest
import com.example.dmsport_android.dto.response.LoginResponse
import com.example.dmsport_android.repository.LoginRepository
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.putPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val pref : SharedPreferences,
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Response<LoginResponse>>()
    val loginResponse : LiveData<Response<LoginResponse>> = _loginResponse

    private val _inputType = MutableLiveData<Int>()
    val inputType : LiveData<Int> = _inputType

    private val _toggle = MutableLiveData<Int>()
    val toggle : LiveData<Int> = _toggle

    fun login(email : String, pw : String){
        val loginRequest = LoginRequest(email, pw)
        viewModelScope.launch(Dispatchers.IO){
            _loginResponse.postValue(loginRepository.login(loginRequest))
        }
    }

    fun initVisible(){
        putPref(pref.edit(), "loginVisible", false)
        _toggle.value = R.drawable.ic_visible_off
        _inputType.value = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun visible(){
        if(getPref(pref, "loginVisible", false) as Boolean){
            _toggle.value = R.drawable.ic_visible_on
            _inputType.value = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "loginVisible", false)
        }else{
            _toggle.value = R.drawable.ic_visible_off
            _inputType.value = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "loginVisible", true)
        }
    }
}