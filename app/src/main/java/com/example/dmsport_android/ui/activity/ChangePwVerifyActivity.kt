package com.example.dmsport_android.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityChangePwVerifyBinding
import com.example.dmsport_android.repository.EmailChangePwRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.EmailChangePwViewModel
import com.example.dmsport_android.viewmodel.factory.EmailChangePwViewModelFactory

class ChangePwVerifyActivity : BaseActivity<ActivityChangePwVerifyBinding>(R.layout.activity_change_pw_verify) {

    private val emailChangePwRepository: EmailChangePwRepository by lazy {
        EmailChangePwRepository()
    }

    private val emailChangePwViewModelFactory: EmailChangePwViewModelFactory by lazy {
        EmailChangePwViewModelFactory(emailChangePwRepository, pref)
    }

    private val emailChangePwViewModel: EmailChangePwViewModel by lazy {
        ViewModelProvider(this, emailChangePwViewModelFactory).get(EmailChangePwViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeVerify()
    }

    fun verifyButton() {
        val auth_code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if (email.isNotEmpty()) {
            emailChangePwViewModel.verify(auth_code, email)
            putPref(pref.edit(), localEmail, email)

        }else{
            snack(binding.root, getString(R.string.verify_caution))
        }
    }

    fun completeButton(){
        val code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if(code.isNotEmpty() && email.isNotEmpty()){
            emailChangePwViewModel.findVerifyEmail(email)
        }else{
            snack(binding.root, getString(R.string.change_pw_bad_request))
        }
    }


    fun observeVerify(){
        emailChangePwViewModel.verifyResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> {
                    putPref(pref.edit(), getPref(pref, localEmail, "").toString(), true)
                }
                UNAUTHORIZED -> snack(binding.root, getString(R.string.verify_unauthorized))
            }
        })
    }


}