package com.github.tatsuyafujisaki.androidplayground.ui.compose.lazygrid

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun LazyListExample() {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged().collect {
                Log.d("👀", "firstVisibleItemIndex = $it")
            }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }.distinctUntilChanged().collect {
            Log.d("👀", "firstVisibleItemScrollOffset = $it")
        }
    }
}
