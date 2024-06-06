package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun CenterVerticallyRowExample() {
    Row(
        modifier = Modifier.height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Hello")
    }
}
