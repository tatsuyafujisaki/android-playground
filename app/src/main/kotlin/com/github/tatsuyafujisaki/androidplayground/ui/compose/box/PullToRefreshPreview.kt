package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PullToRefreshPreview() {
    var text by remember { mutableStateOf("Hello") }
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        text += text
        state.endRefresh()
    }
    Box(
        modifier = Modifier
            .nestedScroll(state.nestedScrollConnection)
            .fillMaxSize(),
    ) {
        if (!state.isRefreshing) {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    Text(text = text)
                }
            }
        }
        PullToRefreshContainer(
            state = state,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}
