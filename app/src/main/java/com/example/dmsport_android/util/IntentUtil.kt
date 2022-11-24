package com.example.dmsport_android.util

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK


fun <T> startIntent(
    context: Context,
    activity: Class<T>,
) {
    context.startActivity(Intent(context, activity))
}

fun <T> startIntentWithFlag(
    context : Context,
    activity: Class<T>,
){
    context.startActivity(Intent(context, activity).addFlags(FLAG_ACTIVITY_NEW_TASK))
}
