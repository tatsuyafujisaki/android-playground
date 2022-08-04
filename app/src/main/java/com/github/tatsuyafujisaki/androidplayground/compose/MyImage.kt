package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun MyImage(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier
    )
}
