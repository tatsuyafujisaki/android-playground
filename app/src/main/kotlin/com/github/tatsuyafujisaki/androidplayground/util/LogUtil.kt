package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import android.webkit.WebResourceError

object LogUtil {
    /**
     * Usage: log("key1", "value1", "key2", "value2")
     * Output: [[key1, value1], [key1, value2]]
     */
    fun log(tag: String, vararg xs: Any) {
        Log.d(tag, xs.toList().chunked(2).toString())
    }

    fun log(tag: String, error: WebResourceError) {
        Log.d(tag, "errorCode: " + error.errorCode + ", description: " + error.description)
    }
}
