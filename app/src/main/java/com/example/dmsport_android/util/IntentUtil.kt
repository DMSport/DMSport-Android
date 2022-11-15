package com.example.dmsport_android.util

import android.content.Context
import android.content.Intent


fun <T> startIntent(
    context: Context,
    activity: Class<T>,
) {
    context.startActivity(Intent(context, activity))
}
