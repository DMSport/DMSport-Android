package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.content.res.AppCompatResources
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
        binding.registerActivity = this
        registerViewModel.initVisible()
        initVisible()
        observeRegister()
    }

    fun nextButton() {
        val name = binding.etRegisterName.text.toString()
        val pw = binding.etRegisterPw.text.toString()
        val pwRe = binding.etRegisterPwRe.text.toString()
        if (name.isNotEmpty() &&
            pw.isNotEmpty() &&
            pwRe.isNotEmpty() &&
            pw.equals(pwRe)
        ) {
            putPref(pref.edit(), localName, name)
            putPref(pref.edit(), localPassword, pw)
            if (getPref(pref, getPref(pref, localEmail, "").toString(), false) as Boolean) {
                registerViewModel.register(pw, name, getPref(pref, localEmail, "").toString())
            } else {
                startIntent(this, VerifyActivity::class.java)
            }
        } else {
            showSnack(binding.root, getString(R.string.register_bad_request))
        }
    }

    fun initVisible() {
        binding.imgRegisterVisiblePw.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_off,
            )
        )
        binding.imgRegisterVisiblePwRe.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_off,
            )
        )
        binding.etRegisterPw.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.etRegisterPw.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun visible() {
        if (registerViewModel.visible()) {
            binding.imgRegisterVisiblePw.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_on,
                )
            )
            binding.etRegisterPw.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            binding.imgRegisterVisiblePw.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_off,
                )
            )
            binding.etRegisterPw.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    fun visibleRe() {
        if (registerViewModel.visibleRe()) {
            binding.imgRegisterVisiblePwRe.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_on,
                )
            )
            binding.etRegisterPwRe.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            binding.imgRegisterVisiblePwRe.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_off,
                )
            )
            binding.etRegisterPwRe.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    fun observeRegister() {
        registerViewModel.registerResponse.observe(this, Observer {
            when (it.code()) {
                CREATED -> {
                    ACCESS_TOKEN = "Bearer ${it.body()!!.access_token}"
                    startIntent(this, MainActivity::class.java)
                    finish()
                }
                BAD_REQUEST -> showSnack(binding.root, getString(R.string.register_bad_request))
            }
        })
    }
}