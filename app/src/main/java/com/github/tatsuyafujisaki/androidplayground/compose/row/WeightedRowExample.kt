package com.github.tatsuyafujisaki.androidplayground.compose.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WeightedRowExample(
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null, onClick = onClick ?: {})
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null
        )
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null
        )
        Spacer(
            Modifier
                .weight(1f)
                .background(Color.Red))
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null
        )
    }
}
