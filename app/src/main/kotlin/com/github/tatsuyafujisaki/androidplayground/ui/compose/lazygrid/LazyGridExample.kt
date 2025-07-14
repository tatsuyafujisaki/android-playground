package com.github.tatsuyafujisaki.androidplayground.ui.compose.lazygrid

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LazyGridExample(modifier: Modifier = Modifier) {
    val lazyListState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        modifier = modifier,
        state = lazyListState
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        }
    }
}
