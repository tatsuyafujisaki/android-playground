package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val innerSize = 8.dp
private val outerSize = 32.dp

// How does the order of clickable() and padding() affect the touch target?
// If clickable() is called before padding() in a Modifier's method chain, the padding is clickable.
// If padding() is called before clickable() in a Modifier's method chain, the padding is NOT clickable.

@Preview(showBackground = true)
@Composable
private fun ClickableAndPaddingExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Magenta)
            .clickable {}
            .padding(all = innerSize)
            .size(size = outerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "1")
    }
}

@Preview(showBackground = true)
@Composable
private fun ClickableAndPaddingExample2() {
    Box(
        modifier = Modifier
            .background(color = Color.Magenta)
            .padding(all = innerSize)
            .clickable {}
            .size(size = outerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "2")
    }
}
