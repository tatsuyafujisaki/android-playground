package com.github.tatsuyafujisaki.androidplayground.ui.compose.button

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TextButtonExample() {
    TextButton(
        onClick = {},
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Cyan,
            contentColor = Color.Magenta,
        )
    ) {
        Text(text = "a")
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton48() {
    TextButton(
        onClick = {},
        modifier = Modifier.minimumInteractiveComponentSize()
    ) {
        Text(text = "a")
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton44() {
    TextButton(
        onClick = {},
        modifier = Modifier.sizeIn(minWidth = 44.dp, minHeight = 44.dp)
    ) {
        Text(text = "a")
    }
}
