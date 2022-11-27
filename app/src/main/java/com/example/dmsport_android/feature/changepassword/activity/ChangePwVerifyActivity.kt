package com.example.dmsport_android.feature.changepassword.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityChangePwVerifyBinding
import com.example.dmsport_android.feature.changepassword.repository.ChangePasswordRepository
import com.example.dmsport_android.feature.changepassword.viewmodel.EmailChangePwViewModel
import com.example.dmsport_android.feature.changepassword.viewmodel.factory.EmailChangePwViewModelFactory
import com.example.dmsport_android.util.*

class ChangePwVerifyActivity : BaseActivity<ActivityChangePwVerifyBinding>(R.layout.activity_change_pw_verify) {

    private val changePasswordRepository: ChangePasswordRepository by lazy {
        ChangePasswordRepository()
    }

    private val emailChangePwViewModelFactory: EmailChangePwViewModelFactory by lazy {
        EmailChangePwViewModelFactory(changePasswordRepository, pref)
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
            showSnack(binding.root, getString(R.string.register_bad_request))
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
