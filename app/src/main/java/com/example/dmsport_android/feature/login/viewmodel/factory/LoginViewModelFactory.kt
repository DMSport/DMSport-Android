package com.example.dmsport_android.feature.login.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.login.repository.LoginRepository
import com.example.dmsport_android.feature.login.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val loginRepository: LoginRepository,
    private val sharedPreferences : SharedPreferences,
) :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        LoginViewModel(loginRepository, sharedPreferences) as T
}