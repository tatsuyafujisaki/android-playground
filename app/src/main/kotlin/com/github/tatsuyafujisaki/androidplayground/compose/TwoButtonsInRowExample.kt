package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun MyButton(
    text: String,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = {},
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun TwoButtonsInRowPreview() {
    Row(modifier = Modifier.fillMaxWidth()) {
        MyButton(
            modifier = Modifier.weight(1f),
            text = "a"
        )
        Spacer(Modifier.width(8.dp))
        MyButton(
            modifier = Modifier.weight(1f),
            text = "b"
        )
    }
}
