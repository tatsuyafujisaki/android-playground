package com.github.tatsuyafujisaki.androidplayground.ui.compose.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun FullscreenDialogExample(onDismissRequest: () -> Unit = {}) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier =
            Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Hello world")
        }
    }
}

@Preview
@Composable
fun FullscreenDialogPreviewExample() {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        FullscreenDialogExample {
            isVisible = false
        }
    }
}
