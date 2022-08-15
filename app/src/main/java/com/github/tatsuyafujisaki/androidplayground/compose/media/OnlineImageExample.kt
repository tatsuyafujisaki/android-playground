package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun OnlineImageExample(
    modifier: Modifier = Modifier,
    url: String,
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val m = if (isCircle) modifier.clip(CircleShape) else modifier

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = if (border != null) {
            m.border(border.first, border.second, if (isCircle) CircleShape else RectangleShape)
        } else {
            m
        },
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun PreviewOnlineImageExample() {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png"
    )
}

@Preview
@Composable
private fun PreviewOnlineCircularImageExample() {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        isCircle = true
    )
}

@Preview
@Composable
private fun PreviewOnlineBorderedImageExample() {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        border = 8.dp to Color.Red
    )
}

@Preview
@Composable
private fun PreviewOnlineCircularBorderedImageExample() {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        isCircle = true,
        border = 8.dp to Color.Red
    )
}
