package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun HorizontalStartCenterEndBox(
    modifier: Modifier = Modifier,
    startContent: @Composable (BoxScope.(Modifier) -> Unit) = {},
    centerContent: @Composable (BoxScope.(Modifier) -> Unit) = {},
    endContent: @Composable BoxScope.(Modifier) -> Unit = {},
) {
    Box(modifier = modifier) {
        startContent(Modifier.align(alignment = Alignment.CenterStart))
        centerContent(Modifier.align(alignment = Alignment.Center))
        endContent(Modifier.align(alignment = Alignment.CenterEnd))
    }
}

@Preview
@Composable
private fun HorizontalStartCenterEndBoxPreview(modifier: Modifier = Modifier) {
    HorizontalStartCenterEndBox(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 48.dp),
        startContent = {
            Text(text = "🍋", modifier = it)
        },
        centerContent = {
            Text(text = "🍊", modifier = it)
        },
        endContent = {
            Row(modifier = it, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🍎")
                Text(text = "🍏")
            }
        },
    )
}
