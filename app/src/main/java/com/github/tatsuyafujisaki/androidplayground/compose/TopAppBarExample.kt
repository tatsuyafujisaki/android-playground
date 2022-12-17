package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBarExample() {
    TopAppBar(
        title = {
            Text("Title")
        },
        actions = {
            Text("Action1")
            Text("Action2")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarExamplePreview() {
    TopAppBarExample()
}
