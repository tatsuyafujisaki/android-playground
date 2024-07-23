package com.github.tatsuyafujisaki.androidplayground.ui.compose.pager

import android.annotation.SuppressLint
import android.view.ViewGroup.LayoutParams
import android.webkit.WebView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Preview
@Composable
fun HorizontalPagerExample() {
    val urls = listOf("https://example.com", "https://google.com")
    val pagerState = rememberPagerState { urls.size }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
    ) { page ->
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WebView(it).apply {
                    layoutParams =
                        LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT,
                        )
                    settings.javaScriptEnabled = true
                }
            },
            update = {
                it.loadUrl(urls[page])
            },
        )
    }
}
