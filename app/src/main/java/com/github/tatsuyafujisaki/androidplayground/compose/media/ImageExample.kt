package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val m = if (isCircle) modifier.clip(CircleShape) else modifier

    Image(
        painter = painterResource(id = id),
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
private fun PreviewImageExample() {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot
    )
}

@Preview
@Composable
private fun PreviewCircularImageExample() {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot,
        isCircle = true
    )
}

@Preview
@Composable
private fun PreviewImageBorderedExample() {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot,
        border = 8.dp to Color.Red
    )
}

@Preview
@Composable
private fun PreviewCircularBorderedImageExample() {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot,
        isCircle = true,
        border = 8.dp to Color.Red
    )
}
