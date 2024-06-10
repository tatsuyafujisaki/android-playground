package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 * @param contentScale clip(CircleShape) with ContentScale.Fit on an image whose aspect ratio is not 1:1 produces an imperfect circle.
 */
@Composable
private fun AsyncImageExample(
    url: String,
    circle: Boolean = false,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = if (circle) ContentScale.Crop else ContentScale.Fit,
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.size(200.dp).circleBorder(isCircle = circle, border = border),
        contentScale = contentScale,
    )
}

private const val url = "https://images.unsplash.com/photo-1558240077-e33b10a16a64"

@Preview
@Composable
private fun OnlineImageExamplePreview() {
    AsyncImageExample(url = url)
}

@Preview
@Composable
private fun OnlineCircularImageExamplePreview() {
    AsyncImageExample(
        url = url,
        circle = true,
    )
}

@Preview
@Composable
private fun OnlineBorderedImageExamplePreview() {
    AsyncImageExample(
        url = url,
        border = 8.dp to Color.Red,
    )
}

@Preview
@Composable
private fun OnlineCircularBorderedImageExamplePreview() {
    AsyncImageExample(
        url = url,
        circle = true,
        border = 8.dp to Color.Red,
    )
}
