package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.layout.Box
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
private fun StartCenterEndBoxExample() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp),
    ) {
        Text(
            text = "🍎",
            modifier = Modifier.align(alignment = Alignment.CenterStart),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            text = "🍊",
            modifier = Modifier.align(alignment = Alignment.Center),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Text(
            text = "🍏",
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}
