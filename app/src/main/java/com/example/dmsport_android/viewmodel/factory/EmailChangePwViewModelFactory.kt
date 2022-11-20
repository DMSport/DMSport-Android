package com.example.dmsport_android.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.ChangePasswordRepository
import com.example.dmsport_android.viewmodel.EmailChangePwViewModel

class EmailChangePwViewModelFactory(
    private val changePasswordRepository: ChangePasswordRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        EmailChangePwViewModel(changePasswordRepository, sharedPreferences) as T

}
