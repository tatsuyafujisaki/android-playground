package com.github.tatsuyafujisaki.androidplayground.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun MyDivider() {
    Divider(color = Yellow, thickness = 10.dp)
}

@Preview
@Composable
fun ClickableSizeExample() {
    Column {
        Text(
            text = "Click me!",
            modifier = Modifier
                .background(Red)
                .clickable {
                    Log.d("Here!", "Clicked!")
                }
                .fillMaxWidth()
        )
        MyDivider()
        Text(
            text = "Click me!",
            modifier = Modifier
                .background(Red)
                .fillMaxWidth()
                .clickable {
                    Log.d("Here!", "Clicked!")
                }
        )
    }
}
