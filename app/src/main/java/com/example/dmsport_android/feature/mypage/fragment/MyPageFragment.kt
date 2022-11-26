package com.example.dmsport_android.feature.mypage.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseFragment
import com.example.dmsport_android.databinding.FragmentMyPageBinding
import com.example.dmsport_android.feature.deleteuser.DeleteUserActivity
import com.example.dmsport_android.feature.changepassword.activity.EmailChangePwActivity
import com.example.dmsport_android.feature.login.activity.LoginActivity
import com.example.dmsport_android.feature.mypage.repository.MyPageRepository
import com.example.dmsport_android.feature.mypage.viewmodel.MyPageViewModel
import com.example.dmsport_android.feature.mypage.viewmodel.factory.MyPageViewModelFactory
import com.example.dmsport_android.util.*

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageRepository: MyPageRepository by lazy {
        MyPageRepository()
    }

    private val myPageViewModelFactory: MyPageViewModelFactory by lazy {
        MyPageViewModelFactory(
            myPageRepository = myPageRepository,
            editor = editor,
        )
    }

    private val myPageViewModel: MyPageViewModel by lazy {
        ViewModelProvider(this, myPageViewModelFactory).get(MyPageViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myPageFragment = this
        binding.myPageViewModel = myPageViewModel
        myPageViewModel.fetchMyPage()
        observeLogout()
        observeDeleteUser()
        observeMyPageResponse()
    }

    override fun onResume() {
        super.onResume()
        showSnackChangePassword()
    }

    fun deleteUserButton(){
        startIntent(this.requireContext(), DeleteUserActivity::class.java)
    }

    private fun showSnackChangePassword(){
        if(isVerified){
            showSnack(
                view = binding.root,
                message = getString(
                    R.string.mypage_complete_change_password,
                ),
            )
            myPageViewModel.initializeVerified()
        }
    }

    fun changePasswordButton(){
        startIntent(
            context = requireContext(),
            activity = EmailChangePwActivity::class.java,
        )
        myPageViewModel.initializeVerified()
    }

    private fun observeLogout() {
        myPageViewModel.logoutResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                NO_CONTENT -> {
                    startIntent(this.requireContext(), LoginActivity::class.java)
                    isLogOuted = true
                    this.requireActivity().finish()
                }
            }
        }
    }

    private fun observeDeleteUser(){
        myPageViewModel.deleteUserResponse.observe(viewLifecycleOwner) {
            when(it.code()){
                NO_CONTENT -> {
                    startIntent(this.requireContext(), LoginActivity::class.java)
                    isDeletedUser = true
                    this.requireActivity().finish()
                }
            }
        }
    }

    private fun observeMyPageResponse(){
        myPageViewModel.myPageResponse.observe(viewLifecycleOwner){
            when(it.code()){
                OK-> {
                    if(it.body()!!.authority != "USER") {
                        myPageViewModel.saveUserAuth(it.body()!!.authority)
                    }
                }
            }
        }
    }
}
