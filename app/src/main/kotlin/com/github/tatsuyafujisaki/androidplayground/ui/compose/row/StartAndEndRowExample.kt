package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun StartAndEndRowExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "🍎",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            text = "🍊",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}
