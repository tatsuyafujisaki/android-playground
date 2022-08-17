package com.github.tatsuyafujisaki.androidplayground.compose.media

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
import com.github.tatsuyafujisaki.androidplayground.compose.preview.BooleanProvider

/**
 * @param contentScale clip(CircleShape) with ContentScale.Fit on an image whose aspect ratio is not 1:1 produces an imperfect circle.
 */
@Composable
fun ImageExample(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null,
    contentScale: ContentScale = if (isCircle) ContentScale.Crop else ContentScale.Fit
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier.circleBorder(isCircle = isCircle, border = border),
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun PreviewImageExample(
    @PreviewParameter(BooleanProvider::class) isCircle: Boolean
) {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot,
        isCircle = isCircle
    )
}

@Preview
@Composable
private fun PreviewImageBorderedExample(
    @PreviewParameter(BooleanProvider::class) isCircle: Boolean
) {
    ImageExample(
        modifier = Modifier.size(200.dp),
        id = R.drawable.ic_android_robot,
        isCircle = isCircle,
        border = 8.dp to Color.Red
    )
}
