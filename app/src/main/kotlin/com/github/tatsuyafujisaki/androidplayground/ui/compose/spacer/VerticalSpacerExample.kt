package com.github.tatsuyafujisaki.androidplayground.ui.compose.spacer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun VerticalSpacerExample() {
    Column {
        Text(text = "🍎")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "🍊")
    }
}
