package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

@Composable
fun IconButtonExample(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
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
private fun IconButtonExamplePreview() {
    IconButtonExample(id = R.drawable.ic_android_robot) {}
}
