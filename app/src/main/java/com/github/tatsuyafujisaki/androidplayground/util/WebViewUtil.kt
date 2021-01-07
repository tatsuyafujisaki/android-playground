package com.github.tatsuyafujisaki.androidplayground.util

import android.annotation.SuppressLint
import android.webkit.WebView

object WebViewUtil {
    // Minimize the function that uses @SuppressLint.
    @SuppressLint("SetJavaScriptEnabled")
    fun WebView.enableJavaScript() {
        settings.javaScriptEnabled = true
    }
}
