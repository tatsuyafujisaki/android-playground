package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun ImageExample(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = if (border != null) {
            modifier.border(border.first, border.second)
        } else {
            modifier
        },
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun PreviewImageExample() {
    ImageExample(id = R.drawable.ic_android_robot)
}

@Preview
@Composable
private fun PreviewImageBorderedExample() {
    ImageExample(
        id = R.drawable.ic_android_robot,
        border = 8.dp to Color.Red
    )
}
