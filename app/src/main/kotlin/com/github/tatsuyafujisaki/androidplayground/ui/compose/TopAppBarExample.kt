package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopAppBarExample() {
    TopAppBar(
        title = {
            Text("Title")
        },
        actions = {
            Text("Action1")
            Text("Action2")
        },
    )
}
