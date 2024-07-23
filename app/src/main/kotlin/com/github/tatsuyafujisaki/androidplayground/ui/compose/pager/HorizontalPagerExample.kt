package com.github.tatsuyafujisaki.androidplayground.ui.compose.pager

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage

@SuppressLint("SetJavaScriptEnabled")
@Preview
@Composable
fun HorizontalPagerExample() {
    val urls = List(3) { RandomImage.getUrl() }
    val pagerState = rememberPagerState(pageCount = { urls.size })
    val uriHandler = LocalUriHandler.current

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = pagerState.pageCount - 1,
        ) {
            Log.d("👀page", it.toString())
            val url = urls[it]
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { uriHandler.openUri(uri = url) },
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .background(Color.Red)
                .align(alignment = Alignment.BottomCenter),
        ) {
            repeat(times = pagerState.pageCount) {
                Box(
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .clip(shape = CircleShape)
                        .background(color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray)
                        .size(size = 16.dp)
                )
            }
        }
    }
}
