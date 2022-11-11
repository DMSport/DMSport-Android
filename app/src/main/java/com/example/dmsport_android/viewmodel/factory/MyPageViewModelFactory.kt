package com.example.dmsport_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.repository.MyPageRepository
import com.example.dmsport_android.viewmodel.MyPageViewModel

class MyPageViewModelFactory(
    private val myPageRepository: MyPageRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MyPageViewModel(myPageRepository) as T

}