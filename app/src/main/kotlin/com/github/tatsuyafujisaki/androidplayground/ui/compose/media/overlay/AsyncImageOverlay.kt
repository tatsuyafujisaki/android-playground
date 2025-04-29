package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.overlay

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage

@Composable
fun AsyncImageOverlay(
    url: String,
    @DrawableRes mask: Int,
    maskAlpha: Float,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
    Image(
        painter = painterResource(id = mask),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alpha = maskAlpha,
    )
}

@Preview
@Composable
private fun AsyncImageOverlayPreview() {
    AsyncImageOverlay(
        url = RandomImage.getUrl(),
        mask = R.drawable.solid_yellow,
        maskAlpha = 0.5f,
        modifier = Modifier
            .aspectRatio(ratio = 1f / 1f)
            .fillMaxSize(),
    )
}
