package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Rectangle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
private fun MyRow(
    horizontalArrangement: Arrangement.Horizontal,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun HorizontalArrangementRowExample() {
    val content = @Composable { _: RowScope ->
        repeat(3) {
            Image(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        MyRow(
            horizontalArrangement = Arrangement.Center,
            content = content,
        )
        MyRow(
            horizontalArrangement = Arrangement.SpaceAround,
            content = content,
        )
        MyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content,
        )
        MyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            content = content,
        )
    }
}
