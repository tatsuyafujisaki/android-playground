package com.github.tatsuyafujisaki.androidplayground.ui.compose.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun IconButtonExample(onClick: (() -> Unit)? = null) {
    IconButton(
        onClick = onClick ?: {},
        enabled = onClick != null,
    ) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
        )
    }
}
