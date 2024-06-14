package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun TwoEqualCellsRow(
    modifier: Modifier = Modifier,
    leftCellModifier: Modifier = Modifier,
    rightCellModifier: Modifier = Modifier,
    leftCellContent: @Composable BoxScope.() -> Unit,
    rightCellContent: @Composable BoxScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = leftCellModifier
                .fillMaxHeight()
                .weight(1f),
            content = leftCellContent,
        )
        Box(
            modifier = rightCellModifier
                .fillMaxHeight()
                .weight(1f),
            content = rightCellContent,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoEqualCellsRowPreview() {
    TwoEqualCellsRow(
        modifier = Modifier.height(height = 100.dp),
        leftCellModifier = Modifier.background(color = Color.Cyan),
        rightCellModifier = Modifier.background(color = Color.Magenta),
        leftCellContent = {
            Text(
                text = "🍎".repeat(n = 10),
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        },
        rightCellContent = {
            Text(
                text = "🍏".repeat(n = 10),
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
    )
}
