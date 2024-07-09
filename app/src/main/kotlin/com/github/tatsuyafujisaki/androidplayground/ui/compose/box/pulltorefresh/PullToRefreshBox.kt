package com.github.tatsuyafujisaki.androidplayground.ui.compose.box.pulltorefresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PullToRefreshBox() {
    var imageUrl by remember { mutableStateOf(value = RandomImage.getUrl(sizeInPixel = 400)) }
    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        imageUrl = RandomImage.getUrl(sizeInPixel = 400)
        pullToRefreshState.endRefresh()
    }

    Box(
        modifier = Modifier
            .nestedScroll(connection = pullToRefreshState.nestedScrollConnection)
            .fillMaxSize(),
    ) {
        if (!pullToRefreshState.isRefreshing) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}
