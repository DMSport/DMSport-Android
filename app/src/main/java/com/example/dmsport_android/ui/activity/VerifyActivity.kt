package com.example.dmsport_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityVerifyBinding
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.RegisterViewModel
import com.example.dmsport_android.viewmodel.factory.RegisterViewModelFactory
import com.google.android.material.snackbar.Snackbar

class VerifyActivity : BaseActivity<ActivityVerifyBinding>(R.layout.activity_verify) {

    private val registerRepository: RegisterRepository by lazy {
        RegisterRepository()
    }

    private val registerViewModelFactory: RegisterViewModelFactory by lazy {
        RegisterViewModelFactory(registerRepository, pref)
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDuplicate()
        observeVerifyEmail()
    }

    fun verifyButton() {
        val email = binding.etVerifyEmail.text.toString()
        if (email.isNotEmpty()) {
            registerViewModel.duplicate(email)
            putPref(pref.edit(), "email", email)
        }
        snack(binding.root, "이메일을 입력해주세요")
    }

    fun completeButton(){
        val code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if(code.isNotEmpty() && email.isNotEmpty()){
            registerViewModel.verify(code, email)
        }
        snack(binding.root, "항목을 확인해주세요")
    }

    fun observeDuplicate(){
        registerViewModel.duplicateResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> registerViewModel.verifyEmail(getPref(pref, "email", "").toString())
                BAD_REQUEST -> snack(binding.root, "잘못된 이메일 형식입니다")
                CONFLICT -> snack(binding.root, "중복된 이메일 입니다")
            }
        })
    }

    fun observeVerifyEmail(){
        registerViewModel.verifyEmailResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> snack(binding.root, "인증 메일이 전송되었습니다")
            }
        })
    }

}