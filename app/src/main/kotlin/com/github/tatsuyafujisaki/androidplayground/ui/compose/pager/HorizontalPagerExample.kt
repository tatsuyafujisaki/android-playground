package com.github.tatsuyafujisaki.androidplayground.ui.compose.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Preview
@Composable
fun HorizontalPagerExample() {
    val urls = listOf("https://en.wikipedia.org", "https://youtube.com")
    val pagerState = rememberPagerState()

    HorizontalPager(
        pageCount = urls.size,
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        WebView(
            state = rememberWebViewState(urls[page]),
            modifier = Modifier.fillMaxSize(),
            onCreated = { it.settings.javaScriptEnabled = true }
        )
    }
}