package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun FilledIconButtonExample() {
    val uriHandler = LocalUriHandler.current

    FilledIconButton(onClick = { uriHandler.openUri("https://example.com") }) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}
