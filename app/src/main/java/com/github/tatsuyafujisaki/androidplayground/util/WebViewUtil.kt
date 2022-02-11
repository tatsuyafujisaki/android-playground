package com.github.tatsuyafujisaki.androidplayground.util

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

object WebViewUtil {
    private const val TAG = "WebViewUtil"

    fun createWebViewClient() = object : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            view.evaluateJavascript("document.documentElement.outerHTML") {
                val html = it.replace("\\u003C", "<")
                Log.d(TAG, html)
            }
        }
    }

    // Minimize the function that uses @SuppressLint.
    @SuppressLint("SetJavaScriptEnabled")
    fun WebView.enableJavaScript() {
        settings.javaScriptEnabled = true
    }
}
