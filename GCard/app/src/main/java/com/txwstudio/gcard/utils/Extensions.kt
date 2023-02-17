package com.txwstudio.gcard.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast

fun Any.logI(message: String) {
    Log.i(this::class.java.simpleName, message)
}

fun Any.logW(message: String) {
    Log.w(this::class.java.simpleName, message)
}

fun Any.logE(message: String) {
    Log.w(this::class.java.simpleName, message)
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}