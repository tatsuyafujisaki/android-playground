package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun ImageExample() {
    Image(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        modifier = Modifier.size(size = 48.dp),
        colorFilter = ColorFilter.tint(color = Red)
    )
}
