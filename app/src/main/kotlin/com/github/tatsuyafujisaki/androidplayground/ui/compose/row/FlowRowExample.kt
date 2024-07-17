package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun FlowRowExample() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        maxItemsInEachRow = 5,
        maxLines = 2,
    ) {
        repeat(times = 100) {
            Text(text = it.toString())
        }
    }
}
