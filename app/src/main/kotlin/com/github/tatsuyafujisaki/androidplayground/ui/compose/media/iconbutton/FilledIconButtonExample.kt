package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.NullableLambdaProvider

@Composable
private fun FilledIconButtonExample(onClick: (() -> Unit)?) {
    FilledIconButton(
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
private fun FilledIconButtonExamplePreview(
    @PreviewParameter(NullableLambdaProvider::class) onClick: (() -> Unit)?
) {
    FilledIconButtonExample(onClick = onClick)
}
