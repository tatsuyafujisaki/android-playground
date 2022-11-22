package com.github.tatsuyafujisaki.androidplayground.compose

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MyWebView(
    title: String,
    url: String,
    popOrFinish: () -> Unit,
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
                        override fun onPageFinished(view: WebView?, url: String?) {
                            canGoBack = canGoBack()
                            canGoForward = canGoForward()
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
