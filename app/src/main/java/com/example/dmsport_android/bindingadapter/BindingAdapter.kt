package com.example.dmsport_android.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("toggle")
fun ImageView.toggle(toggle : Int){
    setImageResource(toggle)
}