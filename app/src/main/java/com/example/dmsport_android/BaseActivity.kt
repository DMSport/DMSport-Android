package com.example.dmsport_android

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId : Int
) : AppCompatActivity() {
    protected val binding : T by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    protected  val pref : SharedPreferences by lazy {
        initPref(this, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }
}