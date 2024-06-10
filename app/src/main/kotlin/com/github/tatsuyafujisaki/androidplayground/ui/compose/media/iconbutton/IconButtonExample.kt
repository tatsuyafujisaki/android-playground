package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.NullableLambdaProvider

@Composable
private fun IconButtonExample(onClick: (() -> Unit)?) {
    IconButton(
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red,
        )
    }
}

@Preview
@Composable
private fun IconButtonExamplePreview(
    @PreviewParameter(NullableLambdaProvider::class) onClick: (() -> Unit)?
) {
    IconButtonExample(onClick = onClick)
}
