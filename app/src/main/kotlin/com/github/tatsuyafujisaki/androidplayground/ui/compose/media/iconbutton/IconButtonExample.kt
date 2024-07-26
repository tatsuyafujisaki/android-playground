package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun IconButtonExample() {
    val uriHandler = LocalUriHandler.current

    IconButton(onClick = { uriHandler.openUri("https://example.com") }) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}
