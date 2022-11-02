package com.example.dmsport_android.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.LoginRepository
import com.example.dmsport_android.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val loginRepository: LoginRepository,
    private val sharedPreferences : SharedPreferences,
) :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository, sharedPreferences) as T
    }
}