package com.github.tatsuyafujisaki.androidplayground.ui.compose.box.pulltorefresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.ui.compose.media.fillScreenHeight
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary#PullToRefreshBox(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.pulltorefresh.PullToRefreshState,androidx.compose.ui.Alignment,kotlin.Function1,kotlin.Function1)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PullToRefreshBoxExample() {
    var isRefreshing by remember { mutableStateOf(value = false) }
    var imageUrl by remember { mutableStateOf(value = RandomImage.getUrl()) }
    val coroutineScope = rememberCoroutineScope()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            coroutineScope.launch {
                delay(100)
                imageUrl = RandomImage.getUrl()
                isRefreshing = false
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillScreenHeight(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
