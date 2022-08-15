package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WebImageExample(
    modifier: Modifier = Modifier,
    url: String,
    border: Pair<Dp, Color>
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.border(border.first, border.second)
    )
}

@Preview
@Composable
private fun PreviewWebImageExample() {
    WebImageExample(
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        border = 8.dp to Color.Red
    )
}
