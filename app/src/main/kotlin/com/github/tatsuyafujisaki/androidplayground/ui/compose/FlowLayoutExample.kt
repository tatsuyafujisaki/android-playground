package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun SampleContent() {
    repeat(30) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray)
                .border(2.dp, Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(it.toString())
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowRowPreview() {
    FlowRow {
        SampleContent()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowColumnPreview() {
    FlowColumn {
        SampleContent()
    }
}
