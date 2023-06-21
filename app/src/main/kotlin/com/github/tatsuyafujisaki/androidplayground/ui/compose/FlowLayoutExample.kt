package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow

@Composable
private fun SampleContent() {
    repeat(30) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.Blue)
                .border(2.dp, Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(it.toString())
        }
    }
}

@Preview
@Composable
fun FlowRowPreview() {
    FlowRow {
        SampleContent()
    }
}

@Preview
@Composable
fun FlowColumnPreview() {
    FlowColumn {
        SampleContent()
    }
}
