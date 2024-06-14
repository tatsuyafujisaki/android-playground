package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun TwoEqualCellsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 100.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Cyan)
                .fillMaxHeight()
                .weight(1f),
        ) {
            Text(
                text = "あ".repeat(n = 10),
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
        Box(
            modifier = Modifier
                .background(color = Color.Magenta)
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = "い".repeat(n = 10),
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
    }
}
