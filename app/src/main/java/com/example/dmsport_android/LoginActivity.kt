package com.example.dmsport_android

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dmsport_android.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginRepository : LoginRepository by lazy {
        LoginRepository()
    }

    private val loginViewModel : LoginViewModel by lazy {
        LoginViewModel(loginRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding.loginActivity = this

        initSplashScreen()
        initPwVisible()
        initLoginButton()
    }

    fun initLoginButton() {
        val email = binding.etLoginEmail.text.toString()
        val pw = binding.etLoginPw.text.toString()
        loginViewModel.login(email, pw)
    }

    fun initPwVisible() {
        if(getPref(pref, "visible", false) as Boolean){
            binding.imgLoginVisible.setBackgroundResource(R.drawable.ic_visible_on)
            binding.etLoginPw.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "visible", false)
        }else{
            binding.imgLoginVisible.setBackgroundResource(R.drawable.ic_visible_off)
            binding.etLoginPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "visible", true)
        }
    }

    private fun initSplashScreen() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
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



}