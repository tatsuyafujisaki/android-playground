package com.github.tatsuyafujisaki.androidplayground.ui.compose.divider

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDividerExample(
    color: Color,
    thickness: Dp,
) {
    HorizontalDivider(
        thickness = thickness,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun HorizontalDividerPreview() {
    Column {
        Text("Hello")
        HorizontalDividerExample(
            color = Color.Yellow,
            thickness = 10.dp
        )
        Text("World")
    }
}
