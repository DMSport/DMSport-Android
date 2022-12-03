package com.example.dmsport_android.feature.home

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityMainBinding
import com.example.dmsport_android.feature.mypage.fragment.MyPageFragment
import com.example.dmsport_android.feature.mypage.repository.MyPageRepository
import com.example.dmsport_android.feature.mypage.viewmodel.MyPageViewModel
import com.example.dmsport_android.feature.mypage.viewmodel.factory.MyPageViewModelFactory
import com.example.dmsport_android.feature.notice.fragment.NoticeFragment
import com.example.dmsport_android.feature.vote.fragment.VoteFragment
import com.example.dmsport_android.util.OK
import com.example.dmsport_android.util.isJoined
import com.example.dmsport_android.util.isLogged
import com.example.dmsport_android.util.showSnack

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val myPageRepository by lazy {
        MyPageRepository()
    }

    private val myPageViewModelFactory by lazy {
        MyPageViewModelFactory(
            myPageRepository = myPageRepository,
            editor = editor,
        )
    }

    private val myPageViewModel by lazy {
        ViewModelProvider(this, myPageViewModelFactory).get(MyPageViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initNavigationBar()
        showSnackBarMainActivity()
        observeMyPageResponse()
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
                    myPageViewModel.fetchMyPage()
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

    private fun showSnackBarMainActivity() {
        if (isLogged) {
            showSnack(binding.root, getString(R.string.login_ok))
            isLogged = false
        } else if (isJoined) {
            showSnack(binding.root, getString(R.string.register_created))
            isJoined = false
        }
    }

    private fun observeMyPageResponse() {
        myPageViewModel.myPageResponse.observe(this) {
            when (it.code()) {
                OK -> if (it.body()!!.authority != "USER") {
                    myPageViewModel.saveUserInfo(
                        it.body()!!.authority,
                        name = it.body()!!.name
                    )
                }
            }
        }
    }
}