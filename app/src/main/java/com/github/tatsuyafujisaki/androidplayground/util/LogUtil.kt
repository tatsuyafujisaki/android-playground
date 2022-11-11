package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log

object LogUtil {
    fun log(vararg xs: String) {
        Log.d("Here!", xs.toList().chunked(2).toString())
    }
}
