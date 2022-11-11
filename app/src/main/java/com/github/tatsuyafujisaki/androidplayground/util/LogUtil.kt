package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log

object LogUtil {
    fun log(map: Map<String, String>) {
        Log.d("Here!", map.toString())
    }
}
