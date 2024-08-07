package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.ui.compose.media.logSizeChanged

@Preview
@Composable
fun Example() {
    Text(text = "Hello", modifier = Modifier.onSizeChanged {
        println("Text size: $it[dp]") // Text size: 87 x 45[dp]
        println("Text width: ${it.width.dp}") // Text width: 87.0.dp
        println("Text height: ${it.height.dp}") // Text height: 45.0.dp
    })
}

@Preview
@Composable
fun MinimumTouchTargetSizeExample() {
    var selected by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = { selected = !selected },
            modifier = Modifier
                .background(color = Color.Cyan)
                .logSizeChanged(),
        )
        RadioButton(
            selected = false,
            onClick = null,
            modifier = Modifier
                .background(color = Color.Magenta)
                .logSizeChanged(),
        )
    }
}
