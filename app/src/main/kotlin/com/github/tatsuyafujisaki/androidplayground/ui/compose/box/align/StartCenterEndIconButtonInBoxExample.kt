package com.github.tatsuyafujisaki.androidplayground.ui.compose.box.align

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun StartCenterEndIconButtonBoxExample() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.align(alignment = Alignment.CenterStart),
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = null,
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier.align(alignment = Alignment.Center),
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = null,
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = null,
            )
        }
    }
}
