package com.example.dmsport_android.feature.deleteuser

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityDeleteUserBinding
import com.example.dmsport_android.feature.login.activity.LoginActivity
import com.example.dmsport_android.feature.mypage.repository.MyPageRepository
import com.example.dmsport_android.feature.mypage.viewmodel.MyPageViewModel
import com.example.dmsport_android.feature.mypage.viewmodel.factory.MyPageViewModelFactory
import com.example.dmsport_android.util.*

class DeleteUserActivity : BaseActivity<ActivityDeleteUserBinding>(R.layout.activity_delete_user) {

    private val myPageRepository : MyPageRepository by lazy {
        MyPageRepository()
    }

    private val myPageViewModelFactory : MyPageViewModelFactory by lazy {
        MyPageViewModelFactory(
            myPageRepository = myPageRepository,
            editor = editor,
        )
    }

    private val myPageViewModel : MyPageViewModel by lazy {
        ViewModelProvider(this, myPageViewModelFactory)[MyPageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDeleteUserResponse()
        binding.deleteUserActivity = this
    }

    fun deleteUserButton() {
        val password = binding.etDeleteUserPw.text.toString()
        if(password.isNotEmpty()){
            val deleteUserRequest = DeleteUserRequest(password)
            myPageViewModel.deleteUser(deleteUserRequest)
        }else{
            showSnack(binding.root, "비밀번호를 입력해주세요")
        }
    }

    private fun observeDeleteUserResponse(){
        myPageViewModel.deleteUserResponse.observe(this){
            when(it.code()){
                NO_CONTENT -> {
                    startIntent(this, LoginActivity::class.java)
                    isDeletedUser = true
                    putPref(pref.edit(), getPref(pref, localEmail, "").toString(), false)
                    finish()
                }
            }
        }
    }
}