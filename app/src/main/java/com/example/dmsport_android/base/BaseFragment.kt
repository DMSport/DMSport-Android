package com.example.dmsport_android.base

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.dmsport_android.util.initPref

abstract class BaseFragment<V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
    ) : Fragment(){

    protected lateinit var binding: V

    protected val pref : SharedPreferences by lazy {
        initPref(requireContext())
    }

    protected val editor : SharedPreferences.Editor by lazy {
        pref.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }
}
