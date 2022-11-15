package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityEmailChangePwBinding
import com.example.dmsport_android.repository.EmailChangePwRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.EmailChangePwViewModel
import com.example.dmsport_android.viewmodel.factory.EmailChangePwViewModelFactory

class EmailChangePwActivity: BaseActivity<ActivityEmailChangePwBinding> (R.layout.activity_email_change_pw) {

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
        binding.emailChangePwActivity = this
        emailChangePwViewModel.initVisible()
        initVisible()
        observeChange()
    }

    fun initVisible() {
        binding.imgChangeVisiblePw.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_off,
            )
        )
        binding.imgChangeVisiblePwRe.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_off,
            )
        )
        binding.etChangePw.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.etChangePw.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun visible() {
        if (emailChangePwViewModel.visible()) {
            binding.imgChangeVisiblePw.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_on,
                )
            )
            binding.etChangePw.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            binding.imgChangeVisiblePw.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_off,
                )
            )
            binding.etChangePw.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    fun visibleRe() {
        if (emailChangePwViewModel.visibleRe()) {
            binding.imgChangeVisiblePwRe.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_on,
                )
            )
            binding.etChangePwRe.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            binding.imgChangeVisiblePwRe.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_off,
                )
            )
            binding.etChangePwRe.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }


    fun nextButton() {
        val new_pw = binding.etChangePw.text.toString()
        val new_pwRe = binding.etChangePwRe.text.toString()
        if (new_pw.isNotEmpty() &&
            new_pwRe.isNotEmpty() &&
            new_pw == new_pwRe
        ) {
            putPref(pref.edit(), localPassword, new_pw)
            if (getPref(pref, getPref(pref, localEmail,"").toString(), false) as Boolean) {
                emailChangePwViewModel.emailChangePw(getPref(pref, localEmail, "").toString(), new_pw)
            } else {
                startIntent(this, VerifyActivity::class.java)
            }
        } else {
            showSnack(binding.root, getString(R.string.change_pw_bad_request))
        }
    }

    fun observeChange() {
        emailChangePwViewModel.emailChangePwResponse.observe(this, Observer {
            Log.d("TEST", it.code().toString()+"hsdsaffa")
            when(it.code()) {
                NO_CONTENT -> {
                    showSnack(binding.root, getString(R.string.change_pw_created))
                    startIntent(this, LoginActivity::class.java)
                    finish()
                }
                UNAUTHORIZED -> showSnack(binding.root, getString(R.string.register_unauthorized))
            }
        })
    }
}