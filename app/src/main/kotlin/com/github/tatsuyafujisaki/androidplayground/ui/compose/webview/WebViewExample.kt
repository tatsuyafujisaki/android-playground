package com.github.tatsuyafujisaki.androidplayground.ui.compose.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewExample(
    title: String,
    url: String,
    onBack: () -> Unit,
    onClick: (() -> Unit)? = null,
) {
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    var webView by remember { mutableStateOf<WebView?>(null) }
    val goBack: () -> Unit = {
        val view = webView
        if (view != null && view.canGoBack()) view.goBack() else onBack()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WebViewTopBar(
                title = title,
                canGoBack = canGoBack,
                canGoForward = canGoForward,
                onNavigationIconClick = onBack,
                onBackClick = goBack,
                onReloadClick = { webView?.reload() },
                onForwardClick = { webView?.goForward() },
            )
        },
    ) { paddingValues ->
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun doUpdateVisitedHistory(
                            view: WebView?,
                            url: String?,
                            isReload: Boolean,
                        ) {
                            canGoBack = view?.canGoBack() == true
                            canGoForward = view?.canGoForward() == true
                        }
                    }
                    onClick?.let {
                        setOnTouchListener { v, _ ->
                            it.invoke()
                            v.performClick()
                        }
                    }
                    webView = this
                }
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            onRelease = { webView = null },
            update = {
                if (it.tag != url) {
                    it.tag = url
                    it.loadUrl(url)
                }
            },
        )
    }
    NavigationBackHandler(
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        onBackCompleted = goBack,
    )
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
