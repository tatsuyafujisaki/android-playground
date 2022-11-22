package com.github.tatsuyafujisaki.androidplayground.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.*

/**
 * @param navigator is defined outside [MyAccompanistWebView] in case the toolbar uses it.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyAccompanistWebView(
    url: String,
    navigator: WebViewNavigator = rememberWebViewNavigator(),
    client: AccompanistWebViewClient = remember { AccompanistWebViewClient() }
) {
    val state = rememberWebViewState(url)

    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        navigator = navigator,
        onCreated = {
            it.settings.javaScriptEnabled = true
        },
        client = client
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyAccompanistWebView() {
    MyAccompanistWebView(
        url = "https://example.com"
    )
}
