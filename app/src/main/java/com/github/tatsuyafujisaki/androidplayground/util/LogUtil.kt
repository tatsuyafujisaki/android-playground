package com.github.tatsuyafujisaki.androidplayground.util

import android.webkit.WebResourceError
import timber.log.Timber

object LogUtil {
    /**
     * Usage: log("key1", "value1", "key2", "value2")
     * Output: [[key1, value1], [key1, value2]]
     */
    fun log(vararg xs: Any) {
        Timber.d(xs.toList().chunked(2).toString())
    }

    fun log(error: WebResourceError) {
        Timber.d("errorCode: " + error.errorCode + ", description: " + error.description)
    }
}
