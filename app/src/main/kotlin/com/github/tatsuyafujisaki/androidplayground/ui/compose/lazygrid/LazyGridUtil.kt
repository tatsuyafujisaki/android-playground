package com.github.tatsuyafujisaki.androidplayground.ui.compose.lazygrid

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable

private fun LazyGridScope.maxSpanItem(content: @Composable LazyGridItemScope.() -> Unit) {
    item(span = { GridItemSpan(maxCurrentLineSpan) }, content = content)
}
