package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.util.Log
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
        observeVerifyEmail()
        observeVerify()
        binding.changePwVerifyActivity = this
    }

    fun verifyButton() {
        val email = binding.etVerifyEmail.text.toString()
        if (email.isNotEmpty()) {
            emailChangePwViewModel.findVerifyEmail(
                email = email,
            )
            putPref(
                editor = pref.edit(),
                key = localEmail,
                value = email,
            )
        } else {
            showSnack(binding.root, getString(R.string.verify_caution))
        }
    }

    fun completeButton(){
        val code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if(code.isNotEmpty() && email.isNotEmpty()){
            emailChangePwViewModel.verify(email, code)
        }else{
            showSnack(binding.root, getString(R.string.change_pw_bad_request))
        }
    }

    fun observeVerifyEmail(){
        emailChangePwViewModel.findVerifyEmailResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> showSnack(binding.root, getString(R.string.duplicate_no_content))
            }
        })
    }

    fun observeVerify(){
        emailChangePwViewModel.verifyResponse.observe(this, Observer {
            Log.d("TEST", it.code().toString())
            when(it.code()){
                NO_CONTENT -> {
                    putPref(pref.edit(), getPref(pref, localEmail, "").toString(), true)
                    startIntent(this, EmailChangePwActivity::class.java)
                    finish()
                }
                UNAUTHORIZED -> showSnack(binding.root, getString(R.string.verify_unauthorized))
            }
        })
    }


}