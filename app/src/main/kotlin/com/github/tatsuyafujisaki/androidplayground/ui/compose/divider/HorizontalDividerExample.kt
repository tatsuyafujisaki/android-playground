package com.github.tatsuyafujisaki.androidplayground.ui.compose.divider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun HorizontalDividerPreview() {
    Column(modifier = Modifier.width(IntrinsicSize.Min)) {
        Text("🍎")
        HorizontalDivider(
            thickness = 8.dp,
            color = Color.Red,
        )
        Text("🍊")
    }
}
