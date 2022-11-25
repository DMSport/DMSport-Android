package com.example.dmsport_android.feature.mypage.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.feature.mypage.repository.MyPageRepository
import com.example.dmsport_android.feature.mypage.viewmodel.MyPageViewModel

class MyPageViewModelFactory(
    private val myPageRepository: MyPageRepository,
    private val editor: SharedPreferences.Editor,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MyPageViewModel(
            myPageRepository = myPageRepository,
            editor = editor,
        ) as T

}