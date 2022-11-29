package com.github.tatsuyafujisaki.androidplayground.compose.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.github.tatsuyafujisaki.androidplayground.compose.WebViewTopBar

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyWebView(
    title: String,
    url: String,
    popOrFinish: () -> Unit,
    onClick: (() -> Unit)? = null
) {
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    var isBackClicked by remember { mutableStateOf(false) }
    var isReloadClicked by remember { mutableStateOf(false) }
    var isForwardClicked by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WebViewTopBar(
                title = title,
                canGoBack = canGoBack,
                canGoForward = canGoForward,
                onNavigationIconClick = popOrFinish,
                onBackClick = {
                    isBackClicked = true
                },
                onReloadClick = {
                    isReloadClicked = true
                },
                onForwardClick = {
                    isForwardClicked = true
                }
            )
        }
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun doUpdateVisitedHistory(
                            view: WebView?,
                            url: String?,
                            isReload: Boolean
                        ) {
                            canGoBack = canGoBack()
                            canGoForward = canGoForward()
                        }
                    }
                    onClick?.let {
                        setOnTouchListener { v, _ ->
                            it.invoke()
                            v.performClick()
                        }
                    }
                    loadUrl(url)
                }
            },
            update = {
                when {
                    isBackClicked -> {
                        isBackClicked = false
                        if (canGoBack) {
                            it.goBack()
                        } else {
                            popOrFinish()
                        }
                    }
                    isReloadClicked -> {
                        isReloadClicked = false
                        it.reload()
                    }
                    isForwardClicked -> {
                        isForwardClicked = false
                        it.goForward()
                    }
                }
            }
        )
    }
    BackHandler {
        isBackClicked = true
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyWebView() {
    MyWebView(
        title = "Title",
        url = "https://example.com",
        popOrFinish = {},
    )
}
