package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun ImageExample(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewImageExample() {
    ImageExample(id = R.drawable.ic_android_robot)
}
