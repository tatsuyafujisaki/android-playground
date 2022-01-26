package com.github.tatsuyafujisaki.androidplayground.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

object WebViewUtil {
    val customizedWebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        }

        override fun onPageFinished(view: WebView, url: String) {
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
        }
    }

    // Minimize the function that uses @SuppressLint.
    @SuppressLint("SetJavaScriptEnabled")
    fun WebView.enableJavaScript() {
        settings.javaScriptEnabled = true
    }
}
