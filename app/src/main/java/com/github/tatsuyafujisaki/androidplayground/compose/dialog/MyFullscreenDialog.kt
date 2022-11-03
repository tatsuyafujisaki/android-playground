package com.github.tatsuyafujisaki.androidplayground.compose.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyFullscreenDialog(
    dismissOnBackPressOrClickOutside: Boolean = true, onDismiss: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        Dialog(
            onDismissRequest = {
                onDismiss()
                isVisible = false
            }, properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPressOrClickOutside,
                dismissOnClickOutside = dismissOnBackPressOrClickOutside,
                // Enables the dialog to expand horizontally to the full.
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Text("Hello world")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMyFullscreenDialog() {
    MyFullscreenDialog()
}
