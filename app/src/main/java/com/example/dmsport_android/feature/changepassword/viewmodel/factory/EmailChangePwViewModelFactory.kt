package com.example.dmsport_android.feature.changepassword.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.changepassword.repository.ChangePasswordRepository
import com.example.dmsport_android.feature.changepassword.viewmodel.EmailChangePwViewModel

@Suppress("UNCHECKED_CAST")
class EmailChangePwViewModelFactory(
    private val changePasswordRepository: ChangePasswordRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        EmailChangePwViewModel(changePasswordRepository, sharedPreferences) as T

}
