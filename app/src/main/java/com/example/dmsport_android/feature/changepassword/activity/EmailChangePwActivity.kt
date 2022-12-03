package com.example.dmsport_android.feature.changepassword.activity

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityEmailChangePwBinding
import com.example.dmsport_android.feature.changepassword.repository.ChangePasswordRepository
import com.example.dmsport_android.feature.changepassword.viewmodel.EmailChangePwViewModel
import com.example.dmsport_android.feature.changepassword.viewmodel.factory.EmailChangePwViewModelFactory
import com.example.dmsport_android.feature.login.activity.LoginActivity
import com.example.dmsport_android.feature.verifyuser.activity.VerifyActivity
import com.example.dmsport_android.util.*

class EmailChangePwActivity :
    BaseActivity<ActivityEmailChangePwBinding>(R.layout.activity_email_change_pw) {

    private val changePasswordRepository: ChangePasswordRepository by lazy {
        ChangePasswordRepository()
    }

    private val emailChangePwViewModelFactory: EmailChangePwViewModelFactory by lazy {
        EmailChangePwViewModelFactory(changePasswordRepository, pref)
    }

    private val emailChangePwViewModel: EmailChangePwViewModel by lazy {
        ViewModelProvider(
            this,
            emailChangePwViewModelFactory
        )[EmailChangePwViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.emailChangePwActivity = this
        emailChangePwViewModel.initVisible()
        initVisible()
        observeChange()
        initializeView()
        observeChangePasswordResponse()
    }

    private fun initializeView(){
        if(isVerified){
            binding.run {
                etChangePw.hint = getString(
                    R.string.change_pw_old_password,
                )
                etChangePwRe.hint = getString(
                    R.string.change_pw_edit_text,
                )
            }
        }
    }

    private fun initVisible() {
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


    fun nextButton() {
        if(isVerified){
            if(
                binding.etChangePw.text.isNotEmpty()
                && binding.etChangePwRe.text.isNotEmpty()
            ){
                emailChangePwViewModel.changePassword(
                    old_password = binding.etChangePw.text.toString(),
                    new_password = binding.etChangePwRe.text.toString(),
                )
            }
        }else {
            val newPassword = binding.etChangePw.text.toString()
            val newPasswordRepeat = binding.etChangePwRe.text.toString()
            if (newPassword.isNotEmpty() &&
                newPasswordRepeat.isNotEmpty() &&
                newPassword == newPasswordRepeat
            ) {
                putPref(editor, localPassword, newPassword)
                if (getPref(pref, getPref(pref, localEmail, "").toString(), false) as Boolean) {
                    emailChangePwViewModel.emailChangePw(
                        email = getPref(pref, localEmail, "").toString(),
                        new_password = newPassword
                    )
                } else {
                    startIntent(this, VerifyActivity::class.java)
                }
            } else {
                showSnack(binding.root, getString(R.string.register_bad_request))
            }
        }
    }

    private fun observeChange() {
        emailChangePwViewModel.emailChangePwResponse.observe(this){
            when (it.code()) {
                NO_CONTENT -> {
                    showSnack(binding.root, getString(R.string.change_pw_created))
                    startIntent(this, LoginActivity::class.java)
                    finish()
                }
                UNAUTHORIZED -> showSnack(binding.root, getString(R.string.register_unauthorized))
            }
        }
    }

    private fun observeChangePasswordResponse(){
        emailChangePwViewModel.changePasswordResponse.observe(this){
            when(it.code()){
                NO_CONTENT->{
                    finish()
                }
                BAD_REQUEST ->{
                    showSnack(
                        view = binding.root,
                        message = getString(
                            R.string.register_bad_request,
                        ),
                    )
                }
                FORBIDDEN ->{
                    showSnack(
                        view = binding.root,
                        message = getString(
                            R.string.login_forbidden,
                        ),
                    )
                }
                PASSWORD_MISMATCH->{
                    showSnack(
                        view = binding.root,
                        message = getString(
                            R.string.login_forbidden,
                        ),
                    )
                }
            }
        }
    }
}
