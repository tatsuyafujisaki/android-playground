package com.github.tatsuyafujisaki.androidplayground.ui.compose.webview

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
 * @param navigator is defined outside [AccompanistWebViewExample] in case the toolbar uses it.
 *
 * <a href="https://github.com/google/accompanist/issues/1150">Accompanist WebView cannot call JavaScript functions.</a>
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AccompanistWebViewExample(
    url: String,
    navigator: WebViewNavigator = rememberWebViewNavigator(),
    client: AccompanistWebViewClient = remember { AccompanistWebViewClient() },
    onClick: (() -> Unit)? = null
) {
    // The Accompanist WebView treats an empty or blank string as a valid URL and shows an blank page instead of an error page.
    // If url is "" and then turns to "example.com", you will see the blank page when you press the Back button.
    if (url.isBlank()) return

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

@Preview
@Composable
private fun AccompanistWebViewExamplePreview() {
    AccompanistWebViewExample(
        url = "https://example.com"
    )
}
