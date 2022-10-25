package com.example.dmsport_android.ui.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.viewmodel.LoginViewModel
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityLoginBinding
import com.example.dmsport_android.util.getPref
import com.example.dmsport_android.util.putPref
import com.example.dmsport_android.repository.LoginRepository
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.factory.LoginViewModelFactory

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginRepository : LoginRepository by lazy {
        LoginRepository()
    }

    private val loginViewModelFactory : LoginViewModelFactory by lazy {
        LoginViewModelFactory(loginRepository)
    }

    private val loginViewModel : LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding.loginActivity = this

        initSplashScreen()
        initPwVisible()
    }

    fun initLoginButton() {
        val email = binding.etLoginEmail.text.toString()
        val pw = binding.etLoginPw.text.toString()
        loginViewModel.login(email, pw)
    }

    fun initRegisterButton(){
        startIntent(this, RegisterActivity::class.java)
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
}