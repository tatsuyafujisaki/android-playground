package com.github.tatsuyafujisaki.androidplayground.ui.compose.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconButtonExample(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    IconButton(
        onClick = onClick ?: {},
        modifier = modifier,
        enabled = onClick != null
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonExamplePreview() {
    IconButtonExample(imageVector = Icons.Default.Android)
}
