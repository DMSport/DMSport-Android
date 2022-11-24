package com.example.dmsport_android.feature.register.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.register.repository.RegisterRepository
import com.example.dmsport_android.feature.register.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RegisterViewModel(registerRepository, sharedPreferences) as T

}