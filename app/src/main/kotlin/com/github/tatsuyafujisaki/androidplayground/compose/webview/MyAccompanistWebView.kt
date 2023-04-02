package com.github.tatsuyafujisaki.androidplayground.compose.webview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

/**
 * @param navigator is defined outside [MyAccompanistWebView] in case the toolbar uses it.
 *
 * <a href="https://github.com/google/accompanist/issues/1150">Accompanist WebView cannot call JavaScript functions.</a>
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyAccompanistWebView(
    url: String,
    navigator: WebViewNavigator = rememberWebViewNavigator(),
    client: AccompanistWebViewClient = remember { AccompanistWebViewClient() },
    onClick: (() -> Unit)? = null
) {
    val state = rememberWebViewState(url)

    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        navigator = navigator,
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
            onClick?.let {
                webView.setOnTouchListener { v, _ ->
                    it.invoke()
                    v.performClick()
                }
            }
        },
        client = client
    )
}

@Preview(showBackground = true)
@Composable
private fun MyAccompanistWebViewPreview() {
    MyAccompanistWebView(
        url = "https://example.com"
    )
}
