package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

/**
 * Reinventing the wheel version of IconButton
 */
@Composable
fun RtwIconButton(@DrawableRes id: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RtwIconButtonPreview() {
    var favorite by remember { mutableStateOf(false) }

    RtwIconButton(
        id = if (favorite) R.drawable.favorite_filled_24 else R.drawable.favorite_24,
        modifier = Modifier
            .minimumInteractiveComponentSize()
            .clickable { favorite = !favorite },
    )
}
