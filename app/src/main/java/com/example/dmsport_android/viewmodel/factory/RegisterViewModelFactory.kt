package com.example.dmsport_android.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerRepository, sharedPreferences) as T
    }
}