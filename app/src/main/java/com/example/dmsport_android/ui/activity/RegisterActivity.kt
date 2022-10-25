package com.example.dmsport_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityRegisterBinding
import com.example.dmsport_android.getPref
import com.example.dmsport_android.putPref

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPwReVisible()
        initPwVisible()
        initPwReVisible()
        binding.registerActivity = this
    }

    fun initPwVisible() {
        if(getPref(pref, "visiblePw", false) as Boolean){
            binding.imgRegisterVisiblePw.setBackgroundResource(R.drawable.ic_visible_on)
            binding.etRegisterPw.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "visiblePw", false)
        }else{
            binding.imgRegisterVisiblePw.setBackgroundResource(R.drawable.ic_visible_off)
            binding.etRegisterPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "visiblePw", true)
        }
    }

    fun initPwReVisible() {
        if(getPref(pref, "visiblePwRe", false) as Boolean){
            binding.imgRegisterVisiblePwRe.setBackgroundResource(R.drawable.ic_visible_on)
            binding.etRegisterPwRe.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
            putPref(pref.edit(), "visiblePwRe", false)
        }else{
            binding.imgRegisterVisiblePwRe.setBackgroundResource(R.drawable.ic_visible_off)
            binding.etRegisterPwRe.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            putPref(pref.edit(), "visiblePwRe", true)
        }
    }

}