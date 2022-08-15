package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun CircularImageExample(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val circleShapeModifier = modifier.clip(CircleShape)

    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = if (border != null) {
            circleShapeModifier.border(border.first, border.second, CircleShape)
        } else {
            circleShapeModifier
        },
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun PreviewCircularImageExample() {
    CircularImageExample(id = R.drawable.ic_android_robot)
}

@Preview
@Composable
private fun PreviewCircularBorderedImageExample() {
    CircularImageExample(
        id = R.drawable.ic_android_robot,
        border = 8.dp to Color.Red
    )
}
