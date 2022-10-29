package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityRegisterBinding
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.RegisterViewModel
import com.example.dmsport_android.viewmodel.factory.RegisterViewModelFactory

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

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
        observeRegister()
        registerViewModel.initVisible()
        binding.registerViewModel = registerViewModel
        binding.registerActivity = this
    }

    fun nextButton() {
        val name = binding.etRegisterName.text.toString()
        val pw = binding.etRegisterPw.text.toString()
        val pwRe = binding.etRegisterPwRe.text.toString()
        if (name.isNotEmpty() && pw.isNotEmpty() && pwRe.isNotEmpty() && pw.equals(pwRe)) {
            putPref(pref.edit(), "name", name)
            putPref(pref.edit(), "pw", pw)
            if(getPref(pref, getPref(pref, "email", "").toString(), false) as Boolean){
                registerViewModel.register(pw, name, getPref(pref, "email", "").toString())
            }else{
                startIntent(this, VerifyActivity::class.java)
            }
        }else{
            snack(binding.root, "항목을 확인해주세요!")
        }
    }

    fun observeRegister(){
        registerViewModel.registerResponse.observe(this, Observer {
            when(it.code()){
                CREATED -> {
                    snack(binding.root, "회원가입이 완료되었습니다")
                    startIntent(this, BottomNavActivity::class.java)
                }
                BAD_REQUEST -> snack(binding.root, "항목을 확인해주세요")
            }
        })
    }

}