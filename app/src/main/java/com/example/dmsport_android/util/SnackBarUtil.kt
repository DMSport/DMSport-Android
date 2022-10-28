package com.example.dmsport_android.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snack(view : View, message : String){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun snackLong(view : View, message : String){
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}