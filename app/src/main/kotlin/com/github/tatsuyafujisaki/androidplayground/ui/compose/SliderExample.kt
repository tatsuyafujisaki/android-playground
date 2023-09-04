package com.github.tatsuyafujisaki.androidplayground.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SliderExample(
    maxValue: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeStarted: () -> Unit,
    onValueChangeFinished: () -> Unit
) {
    var sliderValue by remember { mutableFloatStateOf(0f) }
    var started by remember { mutableStateOf(true) }

    Column {
        Text(text = sliderValue.toString())
        Slider(
            value = sliderValue,
            valueRange = 0f..maxValue,
            onValueChange = {
                if (started) {
                    started = false
                    onValueChangeStarted()
                }
                onValueChange(it)
                sliderValue = it
            },
            onValueChangeFinished = {
                started = true
                onValueChangeFinished()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SliderExamplePreview() {
    SliderExample(
        maxValue = 3f,
        onValueChange = {
            Log.d("SliderExample", "onValueChange() is called. $it")
        },
        onValueChangeStarted = {
            Log.d("SliderExample", "onValueChangeStarted() is called.")
        },
        onValueChangeFinished = {
            Log.d("SliderExample", "onValueChangeFinished() is called.")
        }
    )
}
