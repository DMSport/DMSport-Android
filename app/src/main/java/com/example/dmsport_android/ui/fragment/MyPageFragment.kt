package com.example.dmsport_android.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentMyPageBinding
import com.example.dmsport_android.repository.MyPageRepository
import com.example.dmsport_android.ui.activity.LoginActivity
import com.example.dmsport_android.util.NO_CONTENT
import com.example.dmsport_android.util.isLogOuted
import com.example.dmsport_android.util.snack
import com.example.dmsport_android.util.startIntent
import com.example.dmsport_android.viewmodel.MyPageViewModel
import com.example.dmsport_android.viewmodel.factory.MyPageViewModelFactory

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageRepository: MyPageRepository by lazy {
        MyPageRepository()
    }

    private val myPageViewModelFactory: MyPageViewModelFactory by lazy {
        MyPageViewModelFactory(myPageRepository)
    }

    private val myPageViewModel: MyPageViewModel by lazy {
        ViewModelProvider(this, myPageViewModelFactory).get(MyPageViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myPageViewModel = myPageViewModel
        myPageViewModel.my()
        observeLogout()
    }

    private fun observeLogout() {
        myPageViewModel.logoutResponse.observe(viewLifecycleOwner, Observer {
            when (it.code()) {
                NO_CONTENT -> {
                    startIntent(this.requireContext(), LoginActivity::class.java)
                    isLogOuted = true
                    this.requireActivity().finish()
                }
            }
        })
    }


}
