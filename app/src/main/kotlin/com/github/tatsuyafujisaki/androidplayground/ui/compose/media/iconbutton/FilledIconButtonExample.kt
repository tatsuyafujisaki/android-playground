package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.NullableLambdaProvider

@Composable
private fun FilledIconButtonExample(onClick: (() -> Unit)?) {
    FilledIconButton(
        onClick = onClick ?: {},
        enabled = onClick != null,
    ) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            tint = Color.Green,
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
