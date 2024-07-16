package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.overlay

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.util.RandomImage

@Composable
fun AsyncImageBottomHalfOverlay(
    url: String,
    @DrawableRes mask: Int,
    maskAlpha: Float,
    modifier: Modifier = Modifier,
    additionalAsyncImageModifier: Modifier = Modifier,
    additionalImageModifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = modifier then additionalAsyncImageModifier,
            contentScale = ContentScale.Crop,
        )
        Image(
            painter = painterResource(id = mask),
            contentDescription = null,
            modifier = modifier then additionalImageModifier,
            contentScale = ContentScale.Crop,
            alpha = maskAlpha,
        )
    }
}

@Preview
@Composable
private fun AsyncImageLowerAreaOverlayPreview() {
    AsyncImageBottomHalfOverlay(
        url = RandomImage.getUrl(),
        mask = R.drawable.solid_yellow,
        maskAlpha = 0.5f,
        additionalAsyncImageModifier = Modifier
            .aspectRatio(ratio = 1f / 1f)
            .fillMaxSize(),
        additionalImageModifier = Modifier
            .aspectRatio(ratio = 2f / 1f)
            .fillMaxWidth(),
    )
}
