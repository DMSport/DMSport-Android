package com.example.dmsport_android.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityRegisterBinding
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.util.putPref
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.RegisterViewModel
import com.example.dmsport_android.viewmodel.factory.RegisterViewModelFactory
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val registerRepository : RegisterRepository by lazy {
        RegisterRepository()
    }

    private val registerViewModelFactory : RegisterViewModelFactory by lazy {
        RegisterViewModelFactory(registerRepository, pref)
    }

    private val registerViewModel : RegisterViewModel by lazy {
        ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.registerViewModel = registerViewModel
        registerViewModel.initPwVisible()
        registerViewModel.initPwReVisible()
        binding.registerActivity = this
    }
    
    fun initNextButton(){
        val name = binding.etRegisterName.text.toString()
        val pw = binding.etRegisterPw.text.toString()
        val pwRe = binding.etRegisterPwRe.text.toString()
        if(name.isNotEmpty() && pw.isNotEmpty() && pwRe.isNotEmpty() && pw.equals(pwRe)){
            putPref(pref.edit(), "name", name)
            putPref(pref.edit(), "pw", pw)
            startIntent(this, VerifyActivity::class.java)
        }else{
            Snackbar.make(binding.btRegisterNext, "항목을 확인해주세요!", Snackbar.LENGTH_SHORT).show()
        }
    }
}