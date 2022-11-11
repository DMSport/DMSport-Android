package com.example.dmsport_android.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityDeleteUserBinding
import com.example.dmsport_android.dto.request.DeleteUserRequest
import com.example.dmsport_android.repository.MyPageRepository
import com.example.dmsport_android.util.NO_CONTENT
import com.example.dmsport_android.util.isDeletedUser
import com.example.dmsport_android.util.showSnack
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.MyPageViewModel
import com.example.dmsport_android.viewmodel.factory.MyPageViewModelFactory

class DeleteUserActivity : BaseActivity<ActivityDeleteUserBinding>(R.layout.activity_delete_user) {

    private val myPageRepository : MyPageRepository by lazy {
        MyPageRepository()
    }

    private val myPageViewModelFactory : MyPageViewModelFactory by lazy {
        MyPageViewModelFactory(myPageRepository)
    }

    private val myPageViewModel : MyPageViewModel by lazy {
        ViewModelProvider(this, myPageViewModelFactory).get(MyPageViewModel::class.java)
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
        myPageViewModel.deleteUserResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> {
                    startIntent(this, LoginActivity::class.java)
                    finish()
                    isDeletedUser = true
                }
            }
        })
    }
}