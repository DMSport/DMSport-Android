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
        observeVerify()
        observeRegister()
        binding.verifyActivity = this
    }

    fun verifyButton() {
        val email = binding.etVerifyEmail.text.toString()
        if (email.isNotEmpty()) {
            registerViewModel.duplicate(email)
            putPref(pref.edit(), localEmail, email)
        }else{
            snack(binding.root, getString(R.string.verify_caution))
        }
    }

    fun completeButton(){
        val code = binding.etVerifyCode.text.toString()
        val email = binding.etVerifyEmail.text.toString()
        if(code.isNotEmpty() && email.isNotEmpty()){
            registerViewModel.verify(code, email)
        }else{
            snack(binding.root, getString(R.string.register_bad_request))
        }
    }

    fun observeDuplicate(){
        registerViewModel.duplicateResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> registerViewModel.verifyEmail(getPref(pref, localEmail, "").toString())
                BAD_REQUEST -> snack(binding.root, getString(R.string.duplicate_bad_request))
                CONFLICT -> snack(binding.root, getString(R.string.duplicate_conflict))
            }
        })
    }

    fun observeVerifyEmail(){
        registerViewModel.verifyEmailResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> snack(binding.root, getString(R.string.duplicate_no_content))
            }
        })
    }

    fun observeVerify(){
        registerViewModel.verifyResponse.observe(this, Observer {
            when(it.code()){
                NO_CONTENT -> {
                    registerViewModel.register(
                        getPref(pref, localPassword, "").toString(),
                        getPref(pref, localName, "").toString(),
                        getPref(pref, localEmail, "").toString(),
                    )
                    putPref(pref.edit(), getPref(pref, localEmail, "").toString(), true)
                }
                UNAUTHORIZED -> snack(binding.root, getString(R.string.verify_unauthorized))
            }
        })
    }

    fun observeRegister(){
        registerViewModel.registerResponse.observe(this, Observer {
            when(it.code()){
                CREATED -> {
                    snack(binding.root, getString(R.string.register_created))
                    startIntent(this, BottomNavActivity::class.java)
                }
                BAD_REQUEST ->{
                    snack(binding.root, getString(R.string.register_bad_request))
                    startIntent(this, RegisterActivity::class.java)
                    finish()
                }
                UNAUTHORIZED ->{
                    snack(binding.root, getString(R.string.register_unauthorized))
                }
            }
        })
    }

}