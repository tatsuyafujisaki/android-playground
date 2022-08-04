package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun PreviewMyIconButton() {
    MyIconButton {}
}
