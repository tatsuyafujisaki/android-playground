package com.github.tatsuyafujisaki.androidplayground.ui.compose.divider

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun VerticalDividerExamplePreview() {
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Text(text = "🍎")
        VerticalDivider(
            thickness = 8.dp,
            color = Color.Red,
        )
        Text(text = "🍊")
    }
}
