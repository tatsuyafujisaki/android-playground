package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.background
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

// How does the order of padding() and size() affect the overall size?
// If size() is called before padding() in a Modifier's method chain, the total size is padding + size.
// If padding() is called before size() in a Modifier's method chain, the total size is size.

// How does the order of background() and padding() affect where to color?
// If background() is called before padding() in a Modifier's method chain, the padding is painted.
// If padding() is called before background() in a Modifier's method chain, the padding is NOT painted.

@Preview(showBackground = true)
@Composable
private fun BackgroundAndSizeExample() {
    Box(
        modifier = Modifier
            .size(size = outerSize)
            .background(color = Color.Magenta),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "0")
    }
}

// Total size = size + 2 * padding (2 means both directions.)
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample1() {
    Box(
        modifier = Modifier
            .background(color = Color.Cyan)
            .padding(all = innerSize)
            .size(size = outerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "1")
    }
}

// Total size = size
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample2() {
    Box(
        modifier = Modifier
            .background(color = Color.Cyan)
            .size(size = outerSize)
            .padding(all = innerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "2")
    }
}

// Total size = size + 2 * padding (2 means both directions.)
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample3() {
    Box(
        modifier = Modifier
            .padding(all = innerSize)
            .background(color = Color.Cyan)
            .size(size = outerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "3")
    }
}

// Total size = size + 2 * padding (2 means both directions.)
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample4() {
    Box(
        modifier = Modifier
            .padding(all = innerSize)
            .size(size = outerSize)
            .background(color = Color.Cyan),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "4")
    }
}

// Total size = size
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample5() {
    Box(
        modifier = Modifier
            .size(size = outerSize)
            .background(color = Color.Cyan)
            .padding(all = innerSize),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "5")
    }
}

// Total size = size
@Preview(showBackground = true)
@Composable
private fun BackgroundAndPaddingAndSizeExample6() {
    Box(
        modifier = Modifier
            .size(size = outerSize)
            .padding(all = innerSize)
            .background(color = Color.Cyan),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "6")
    }
}
