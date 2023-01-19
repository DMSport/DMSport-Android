package com.example.dmsport_android.feature.verifyuser.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityVerifyBinding
import com.example.dmsport_android.feature.home.MainActivity
import com.example.dmsport_android.feature.register.activity.RegisterActivity
import com.example.dmsport_android.feature.register.repository.RegisterRepository
import com.example.dmsport_android.feature.register.viewmodel.RegisterViewModel
import com.example.dmsport_android.feature.register.viewmodel.factory.RegisterViewModelFactory
import com.example.dmsport_android.util.*

class VerifyActivity : BaseActivity<ActivityVerifyBinding>(R.layout.activity_verify) {

    private val registerRepository: RegisterRepository by lazy {
        RegisterRepository()
    }

    private val registerViewModelFactory: RegisterViewModelFactory by lazy {
        RegisterViewModelFactory(registerRepository, pref)
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, registerViewModelFactory)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDuplicate()
        observeVerifyEmail()
        observeVerify()
        observeRegister()
        binding.verifyActivity = this
    }

    fun verifyButton() {
        val email = binding.etVerifyEmail.text.toString()
        if (email.isNotEmpty()) {
            registerViewModel.emailDuplicate(email)
            putPref(pref.edit(), localEmail, email)
        }else{
            showSnack(binding.root, getString(R.string.verify_caution))
        }
    }

    fun completeButton(){
        val code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if(email.isNotEmpty() && code.isNotEmpty()){
            registerViewModel.verifyEmail(email, code)
        }else{
            showSnack(binding.root, getString(R.string.register_bad_request))
        }
    }

    private fun observeDuplicate(){
        registerViewModel.duplicateResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> registerViewModel.sendVerifyEmail(
                    getPref(
                        preferences = pref,
                        key = localEmail,
                        value = ""
                    ).toString()
                )
                BAD_REQUEST -> showSnack(binding.root, getString(R.string.duplicate_bad_request))
                CONFLICT -> showSnack(binding.root, getString(R.string.duplicate_conflict))
            }
        }
    }

    private fun observeVerifyEmail(){
        registerViewModel.verifyEmailResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> showSnack(binding.root, getString(R.string.duplicate_no_content))
            }
        }
    }

    private fun observeVerify(){
        registerViewModel.verifyResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> {
                    registerViewModel.register(
                        getPref(pref, localPassword, "").toString(),
                        getPref(pref, localName, "").toString(),
                        getPref(pref, localEmail, "").toString(),
                    )
                    putPref(pref.edit(), getPref(pref, localEmail, "").toString(), true)
                }
                UNAUTHORIZED -> showSnack(binding.root, getString(R.string.verify_unauthorized))
            }
        }
    }

    private fun observeRegister(){
        registerViewModel.registerResponse.observe(this) {
            when (it.code()) {
                CREATED -> {
                    isJoined = true
                    startIntent(this, MainActivity::class.java)
                    ACCESS_TOKEN = "Bearer $ACCESS_TOKEN"
                }
                BAD_REQUEST -> {
                    showSnack(binding.root, getString(R.string.register_bad_request))
                    startIntent(this, RegisterActivity::class.java)
                    finish()
                }
                UNAUTHORIZED -> {
                    showSnack(binding.root, getString(R.string.register_unauthorized))
                }
            }
        }
    }

}