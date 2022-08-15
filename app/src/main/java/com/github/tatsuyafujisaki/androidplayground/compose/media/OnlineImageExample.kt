package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.compose.preview.BooleanProvider

@Composable
fun OnlineImageExample(
    modifier: Modifier = Modifier,
    url: String,
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier.circleBorder(isCircle = isCircle, border = border),
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun PreviewOnlineImageExample(
    @PreviewParameter(BooleanProvider::class) isCircle: Boolean
) {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        isCircle = isCircle
    )
}

@Preview
@Composable
private fun PreviewOnlineBorderedImageExample(
    @PreviewParameter(BooleanProvider::class) isCircle: Boolean
) {
    OnlineImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://developer.android.com/static/images/brand/Android_Robot.png",
        isCircle = isCircle,
        border = 8.dp to Color.Red
    )
}
