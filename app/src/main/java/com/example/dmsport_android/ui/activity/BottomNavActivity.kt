package com.example.dmsport_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dmsport_android.R
import com.example.dmsport_android.databinding.ActivityBottomNavBinding
import com.example.dmsport_android.ui.fragment.MyPageFragment
import com.example.dmsport_android.ui.fragment.NoticeFragment
import com.example.dmsport_android.ui.fragment.VoteFragment

class BottomNavActivity : AppCompatActivity() {

    private val binding : ActivityBottomNavBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_bottom_nav)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
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