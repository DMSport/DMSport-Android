package com.example.dmsport_android

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<Response<Void>>()
    private val loginResponse : LiveData<Response<Void>> = _loginResponse

    fun login(email : String, pw : String){
        val loginRequest = LoginRequest(email, pw)
        if(email.equals("user") && pw.equals("user")){
            Log.d("TEST", "success")
        }else{
//            viewModelScope.launch() {
//                loginRepository.login(loginRequest)
//            }
        }
    }
}