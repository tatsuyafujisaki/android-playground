package com.github.tatsuyafujisaki.androidplayground.compose.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.github.tatsuyafujisaki.androidplayground.compose.WebViewTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewExample(
    title: String,
    url: String,
    onBack: () -> Unit,
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
                onNavigationIconClick = onBack,
                onBackClick = { isBackClicked = true },
                onReloadClick = { isReloadClicked = true },
                onForwardClick = { isForwardClicked = true }
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
                            canGoBack = view?.canGoBack() ?: false
                            canGoForward = view?.canGoForward() ?: false
                        }
                    }
                    onClick?.let {
                        setOnTouchListener { v, _ ->
                            it.invoke()
                            v.performClick()
                        }
                    }
                }
            },
            update = {
                when {
                    isBackClicked -> {
                        isBackClicked = false
                        if (it.canGoBack()) it.goBack() else onBack()
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
                it.loadUrl(url)
            }
        )
    }
    BackHandler {
        isBackClicked = true
    }
}

@Preview
@Composable
private fun WebViewExamplePreview() {
    WebViewExample(
        title = "Title",
        url = "https://news.google.com",
        onBack = {},
    )
}
