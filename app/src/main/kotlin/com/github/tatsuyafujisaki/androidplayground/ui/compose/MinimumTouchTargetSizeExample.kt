package com.github.tatsuyafujisaki.androidplayground.ui.compose

import android.util.Log
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MinimumTouchTargetSizeExample() {
    var state by remember { mutableStateOf(false) }
    RadioButton(
        selected = state,
        onClick = { state = true },
        modifier =
            Modifier.onSizeChanged {
                Log.d("Clickable RadioButton", "Size $it")
            },
    )

    RadioButton(
        selected = state,
        onClick = null,
        modifier =
            Modifier.onSizeChanged {
                Log.d("Not clickable RadioButton", "Size $it")
            },
    )
}
