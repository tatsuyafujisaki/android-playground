package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.BooleanProvider

@Composable
private fun CircleBorderImageExample(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
    circle: Boolean = false,
    border: Pair<Dp, Color>? = null,
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier.circleBorder(isCircle = circle, border = border),
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
private fun NonBorderedImageExamplePreview(
    @PreviewParameter(BooleanProvider::class) circle: Boolean,
) {
    CircleBorderImageExample(
        id = R.drawable.ic_android_robot,
        modifier = Modifier.size(size = 200.dp),
        circle = circle,
    )
}

@Preview
@Composable
private fun BorderedImageExamplePreview(
    @PreviewParameter(BooleanProvider::class) circle: Boolean,
) {
    CircleBorderImageExample(
        id = R.drawable.ic_android_robot,
        modifier = Modifier.size(size = 200.dp),
        circle = circle,
        border = 8.dp to Color.Red,
    )
}
