package com.example.dmsport_android.feature.login.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityLoginBinding
import com.example.dmsport_android.feature.changepassword.activity.ChangePwVerifyActivity
import com.example.dmsport_android.feature.home.MainActivity
import com.example.dmsport_android.feature.login.repository.LoginRepository
import com.example.dmsport_android.feature.login.viewmodel.LoginViewModel
import com.example.dmsport_android.feature.login.viewmodel.factory.LoginViewModelFactory
import com.example.dmsport_android.feature.register.activity.RegisterActivity
import com.example.dmsport_android.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    var email : String? = null

    private val loginRepository: LoginRepository by lazy {
        LoginRepository()
    }

    private val loginViewModelFactory: LoginViewModelFactory by lazy {
        LoginViewModelFactory(loginRepository, pref)
    }

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding.loginActivity = this
        loginViewModel.run {
            initVisible()
            initUserAuth()
        }
        initVisible()
        observeLogin()
        initSplashScreen()
        snackBar()
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
        binding.imgLoginVisible.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.ic_visible_off,
            )
        )
        binding.etLoginPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun visible(){
        if(loginViewModel.visible()){
            binding.imgLoginVisible.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_visible_on,
                )
            )
            binding.etLoginPw.inputType = InputType.TYPE_CLASS_TEXT
        }else{
            initVisible()
        }
    }

    fun forgotPw() {
        startIntent(this, ChangePwVerifyActivity::class.java)
    }

    private fun observeLogin() {
        loginViewModel.loginResponse.observe(this){
            when (it.code()) {
                OK -> {
                    ACCESS_TOKEN = "Bearer ${it.body()!!.access_token}"
                    REFRESH_TOKEN = it.body()!!.refresh_token
                    isLogged = true
                    startIntent(this, MainActivity::class.java)
                    finish()
                }
                FORBIDDEN -> showSnack(binding.root, getString(R.string.login_forbidden))
                NOT_FOUND -> showSnack(binding.root, getString(R.string.login_not_found))
            }
        }
    }

    private fun snackBar(){
        if(isLogOuted){
            showSnack(binding.root, "로그아웃 되었습니다!")
            isLogOuted = false
        }else if(isDeletedUser){
            showSnack(binding.root, "성공적으로 진행되었습니다!")
            isDeletedUser = false
        }
    }
}