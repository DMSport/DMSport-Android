package com.example.dmsport_android.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnack(
    view : View,
    message : String,
){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}