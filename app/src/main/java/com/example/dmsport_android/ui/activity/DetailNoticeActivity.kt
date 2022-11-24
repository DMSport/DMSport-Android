package com.example.dmsport_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dmsport_android.R
import com.example.dmsport_android.base.BaseActivity
import com.example.dmsport_android.databinding.ActivityDetailNoticeBinding

class DetailNoticeActivity : BaseActivity<ActivityDetailNoticeBinding>(
    R.layout.activity_detail_notice,
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_notice)

    }
}