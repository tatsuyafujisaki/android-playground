package com.github.tatsuyafujisaki.androidplayground.ui.compose.spacer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun HorizontalSpacerExample() {
    Row {
        Text(text = "🍎")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "🍊")
    }
}
