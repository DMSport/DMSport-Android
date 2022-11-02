package com.example.dmsport_android.ui.activity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMainBinding
import com.example.dmsport_android.ui.fragment.MyPageFragment
import com.example.dmsport_android.ui.fragment.NoticeFragment
import com.example.dmsport_android.ui.fragment.VoteFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initNavigationBar()
    }

    private fun initNavigationBar() {
        supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, VoteFragment()).commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.vote -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, VoteFragment()).commitAllowingStateLoss()
                }
                R.id.notice -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, NoticeFragment()).commitAllowingStateLoss()
                }
                R.id.myPage -> {
                    supportFragmentManager.beginTransaction().replace(R.id.menu_frame_layout, MyPageFragment()).commitAllowingStateLoss()
                }
            }
            true
        }
    }
}