package com.example.dmsport_android.ui.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.text.InputType
import android.util.Log
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
        visible()
        binding.loginActivity = this
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

    fun visible(){
        if(loginViewModel.visible()){
            binding.imgLoginVisible.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_visible_on))
            binding.etLoginPw.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        }else{
            binding.imgLoginVisible.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_visible_off))
            binding.etLoginPw.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    private fun observeLogin() {
        loginViewModel.loginResponse.observe(this, Observer {
            when (it.code()) {
                OK -> {
                    snack(binding.root, "로그인에 성공했습니다!")
                    startIntent(this, BottomNavActivity::class.java)
                    finish()
                }
                BAD_REQUEST -> snack(binding.root, "이메일 또는 비밀번호가 잘못되었습니다")
                NOT_FOUND -> snack(binding.root, "존재하지 않는 회원입니다")
            }
        })
    }
}