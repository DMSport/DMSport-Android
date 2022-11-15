package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.text.InputType
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

    fun nextButton() {
        val new_pw = binding.etChangePw.text.toString()
        val new_pwRe = binding.etChangePwRe.text.toString()
        val email = getPref(pref, getPref(pref, localEmail, "").toString(), false)
        if (new_pw.isNotEmpty() &&
            new_pwRe.isNotEmpty() &&
            new_pw.equals(new_pwRe)
        ) {
            putPref(pref.edit(), localPassword, new_pw)
            if (getPref(pref, getPref(pref, localEmail,"").toString(), false) as Boolean) {
                emailChangePwViewModel.emailChangePw(email.toString(), new_pw)
            } else {
                startIntent(this, VerifyActivity::class.java)
            }
        } else {
            showSnack(binding.root, getString(R.string.change_pw_bad_request))
        }
    }

    fun initVisible() {
        binding.imgChangeVisiblePw.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_on,
            )
        )
        binding.imgChangeVisiblePwRe.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_on,
            )
        )
        binding.etChangePw.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        binding.etChangePwRe.inputType =
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

    fun observeChange() {
        emailChangePwViewModel.emailChangePwResponse.observe(this, Observer {
            when(it.code()) {
                CREATED -> {
                    showSnack(binding.root, getString(R.string.change_pw_created))
                    startIntent(this, LoginActivity::class.java)
                }
                BAD_REQUEST -> showSnack(binding.root, getString(R.string.change_pw_bad_request))
            }
        })
    }
}