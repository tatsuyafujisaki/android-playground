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
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun ImageOverlay(
    @DrawableRes main: Int,
    @DrawableRes mask: Int,
    maskAlpha: Float,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = main),
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
private fun ImageOverlayPreview() {
    ImageOverlay(
        main = R.drawable.ic_android_robot,
        mask = R.drawable.solid_yellow,
        maskAlpha = 0.5f,
        modifier = Modifier
            .aspectRatio(ratio = 1f / 1f)
            .fillMaxSize(),
    )
}
