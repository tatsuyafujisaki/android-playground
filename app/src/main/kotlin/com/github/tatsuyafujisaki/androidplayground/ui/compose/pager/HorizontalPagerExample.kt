package com.github.tatsuyafujisaki.androidplayground.ui.compose.pager

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
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

private val URLS = List(size = 3) { RandomImage.getUrl() }

@Preview
@Composable
fun HorizontalPagerExample() {
    val pagerState = rememberPagerState(pageCount = { URLS.size })
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            Log.d("👀", "Page changed to $it")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = pagerState.pageCount - 1,
        ) {
            AsyncImage(
                model = URLS[it],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { uriHandler.openUri(uri = URLS[it]) },
                contentScale = ContentScale.Crop,
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .align(alignment = Alignment.BottomCenter),
        ) {
            repeat(times = pagerState.pageCount) {
                Spacer(
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .clip(shape = CircleShape)
                        .background(color = if (it == pagerState.currentPage) Color.LightGray else Color.DarkGray)
                        .size(size = 16.dp)
                )
            }
        }
    }
}
