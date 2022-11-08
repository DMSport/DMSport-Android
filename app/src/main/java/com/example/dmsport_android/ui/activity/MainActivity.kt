package com.example.dmsport_android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMainBinding
import com.example.dmsport_android.ui.fragment.MyPageFragment
import com.example.dmsport_android.ui.fragment.NoticeFragment
import com.example.dmsport_android.ui.fragment.VoteFragment
import com.example.dmsport_android.util.isLogged
import com.example.dmsport_android.util.snack

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initNavigationBar()
        snackBar()
    }

    private fun initNavigationBar() {
        supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, VoteFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.vote -> {
                    replaceFragment(VoteFragment())
                }
                R.id.notice -> {
                    replaceFragment(NoticeFragment())
                }
                R.id.myPage -> {
                    replaceFragment(MyPageFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment): Int {
        return supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, fragment)
            .commitAllowingStateLoss()
    }

    fun snackBar() {
        if(isLogged){
            snack(binding.root, getString(R.string.login_ok))
            isLogged = false
        }
    }

}