package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log

object LogUtil {
    /**
     * Usage: log("key1", "value1", "key2", "value2")
     * Output: [[key1, value1], [key1, value2]]
     */
    fun log(vararg xs: Any) {
        Log.d("Here!", xs.toList().chunked(2).toString())
    }
}
