package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun MyIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun MyIconButtonPreview() {
    MyIconButton(id = R.drawable.ic_android_robot) {}
}
