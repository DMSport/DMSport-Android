package com.example.dmsport_android.ui.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.viewmodel.LoginViewModel
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityLoginBinding
import com.example.dmsport_android.repository.LoginRepository
import com.example.dmsport_android.util.*
import com.example.dmsport_android.viewmodel.factory.LoginViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginRepository: LoginRepository by lazy {
        LoginRepository()
    }

    private val loginViewModelFactory: LoginViewModelFactory by lazy {
        LoginViewModelFactory(loginRepository, pref)
    }

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding.loginActivity = this
        loginViewModel.initVisible()
        initVisible()
        observeLogin()
        initSplashScreen()
    }

    fun login() {
        val email = binding.etLoginEmail.text.toString()
        val pw = binding.etLoginPw.text.toString()
        if (email.isNotEmpty() && pw.isNotEmpty()) {
            loginViewModel.login(email, pw)
        }
    }

    fun registerText() {
        startIntent(this, RegisterActivity::class.java)
    }

    private fun initSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).run {
                    interpolator = AnticipateInterpolator()
                    duration = 1500L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    private fun initVisible(){
        binding.imgLoginVisible.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_visible_off))
        binding.etLoginPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun visible(){
        if(loginViewModel.visible()){
            binding.imgLoginVisible.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_visible_on))
            binding.etLoginPw.inputType = InputType.TYPE_CLASS_TEXT
        }else{
            initVisible()
        }
    }

    fun forgotPw() {
        startIntent(this, ChangePwVerifyActivity::class.java)
    }

    private fun observeLogin() {
        loginViewModel.loginResponse.observe(this, Observer {
            when (it.code()) {
                OK -> {
                    startIntent(this, MainActivity::class.java)
                    finish()
                }
                BAD_REQUEST -> snack(binding.root, getString(R.string.login_bad_request))
                NOT_FOUND -> snack(binding.root, getString(R.string.login_not_found))
            }
        })
    }
}