package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityVerifyBinding
import com.example.dmsport_android.repository.RegisterRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.RegisterViewModel
import com.example.dmsport_android.viewmodel.factory.RegisterViewModelFactory

class VerifyActivity : BaseActivity<ActivityVerifyBinding>(R.layout.activity_verify) {

    private val registerRepository: RegisterRepository by lazy {
        RegisterRepository()
    }

    private val registerViewModelFactory: RegisterViewModelFactory by lazy {
        RegisterViewModelFactory(
            registerRepository = registerRepository,
            sharedPreferences = pref,
        )
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(
            this,
            registerViewModelFactory,
        ).get(RegisterViewModel::class.java)
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
            putPref(
                editor = pref.edit(),
                key = localEmail,
                value = email,
            )
        } else {
            showSnack(
                view = binding.root,
                message = getString(R.string.verify_caution),
            )
        }
    }

    fun completeButton() {
        val email = binding.etVerifyEmail.text.toString()
        val code = binding.etVerifyCode.text.toString()
        if (code.isNotEmpty() && email.isNotEmpty()) {
            registerViewModel.verifyEmail(
                email = email,
                code = code,
            )
        } else {
            showSnack(
                view = binding.root,
                message = getString(R.string.register_bad_request),
            )
        }
    }

    fun observeDuplicate() {
        registerViewModel.duplicateResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> registerViewModel.sendVerifyEmail(
                    getPref(
                        preferences = pref,
                        key = localEmail,
                        value = "",
                    ).toString()
                )
                BAD_REQUEST -> showSnack(
                    view = binding.root,
                    message = getString(R.string.duplicate_bad_request),
                )
                CONFLICT -> showSnack(
                    view = binding.root,
                    message = getString(R.string.duplicate_conflict),
                )
            }
        }
    }

    fun observeVerifyEmail() {
        registerViewModel.verifyEmailResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> showSnack(
                    view = binding.root,
                    message = getString(R.string.duplicate_no_content),
                )
            }
        }
    }

    fun observeVerify() {
        registerViewModel.verifyResponse.observe(this) {
            when (it.code()) {
                NO_CONTENT -> {
                    registerViewModel.register(
                        password = getPref(
                            preferences = pref,
                            key = localPassword,
                            value = "",
                        ).toString(),
                        name = getPref(
                            preferences = pref,
                            key = localName,
                            value = "",
                        ).toString(),
                        email = getPref(
                            preferences = pref,
                            key = localEmail,
                            value = "",
                        ).toString(),
                    )
                    putPref(
                        editor = pref.edit(),
                        key = getPref(
                            preferences = pref,
                            key = localEmail,
                            value = "",
                        ).toString(),
                        value = true,
                    )
                }
                UNAUTHORIZED -> showSnack(
                    view = binding.root,
                    message = getString(R.string.verify_unauthorized),
                )
            }
        }
    }

    fun observeRegister() {
        registerViewModel.registerResponse.observe(this) {
            when (it.code()) {
                CREATED -> {
                    isJoined = true
                    startIntent(this, MainActivity::class.java)
                    ACCESS_TOKEN = "Bearer $ACCESS_TOKEN"
                }
                BAD_REQUEST -> {
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.register_bad_request),
                    )
                    startIntent(
                        context = this,
                        activity = RegisterActivity::class.java,
                    )
                    finish()
                }
                UNAUTHORIZED -> {
                    showSnack(
                        view = binding.root,
                        message = getString(R.string.register_unauthorized),
                    )
                }
            }
        }
    }
}