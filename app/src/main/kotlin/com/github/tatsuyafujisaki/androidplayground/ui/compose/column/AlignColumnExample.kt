package com.github.tatsuyafujisaki.androidplayground.ui.compose.column

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlignColumnExample(
    content: @Composable ColumnScope.() -> Unit,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .verticalScroll(rememberScrollState())
            .clickable(enabled = onClick != null, onClick = onClick ?: {}),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun AlignColumnExamplePreview() {
    CenterColumnExample(
        content = {
            Text("Bacon", modifier = Modifier.align(Alignment.Start))
            Text("Lettuce")
            Text("Tomato", modifier = Modifier.align(Alignment.End))
        }
    )
}
