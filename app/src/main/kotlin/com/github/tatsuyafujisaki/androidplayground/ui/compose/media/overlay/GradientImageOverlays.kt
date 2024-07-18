package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.overlay

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

private fun Modifier.mask1() = this then Modifier.drawWithCache {
    val brush = Brush.verticalGradient(
        colors = listOf(Color.Red, Color.Green, Color.Blue),
        startY = 0f,
        endY = size.height
    )
    onDrawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.Multiply)
    }
}

private fun Modifier.mask2() = this then Modifier.drawWithCache {
    val brush = Brush.verticalGradient(
        0.0f to Color.Red,
        0.5f to Color.Green,
        1.0f to Color.Blue,
        startY = 0f,
        endY = size.height
    )
    onDrawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.Multiply)
    }
}

@Composable
private fun GradientImageOverlay(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
private fun GradientImageOverlayPreview1() {
    GradientImageOverlay(
        id = R.drawable.star_24,
        modifier = Modifier
            .mask1()
            .aspectRatio(ratio = 1f / 1f)
            .fillMaxSize(),
    )
}

@Preview
@Composable
private fun GradientImageOverlayPreview2() {
    GradientImageOverlay(
        id = R.drawable.star_24,
        modifier = Modifier
            .mask2()
            .aspectRatio(ratio = 1f / 1f)
            .fillMaxSize(),
    )
}
