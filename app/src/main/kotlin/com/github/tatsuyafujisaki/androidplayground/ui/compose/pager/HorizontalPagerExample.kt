package com.github.tatsuyafujisaki.androidplayground.ui.compose.pager

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage

@SuppressLint("SetJavaScriptEnabled")
@Preview
@Composable
fun HorizontalPagerExample() {
    val urls = List(3) { RandomImage.getUrl() }
    val pagerState = rememberPagerState(pageCount = { urls.size })
    val uriHandler = LocalUriHandler.current

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        beyondViewportPageCount = pagerState.pageCount - 1,
    ) { page ->
        val url = urls[page]

        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable { uriHandler.openUri(uri = url) },
            contentScale = ContentScale.Crop,
        )
    }
}
