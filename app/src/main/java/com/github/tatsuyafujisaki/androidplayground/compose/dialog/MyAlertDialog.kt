package com.github.tatsuyafujisaki.androidplayground.compose.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyAlertDialog(
    text: String,
    title: String? = null,
    confirmButtonText: String = stringResource(android.R.string.ok),
    dismissButtonText: String? = null,
    dismissOnBackPressOrClickOutside: Boolean = true,
    onConfirmButtonClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
                isVisible = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButtonClick()
                        isVisible = false
                    }
                ) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = dismissButtonText?.let {
                {
                    TextButton(
                        onClick = {
                            onDismiss()
                            isVisible = false
                        }
                    ) {
                        Text(it)
                    }
                }
            },
            title = title?.let {
                {
                    Text(it)
                }
            },
            text = {
                Text(text)
            },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPressOrClickOutside,
                dismissOnClickOutside = dismissOnBackPressOrClickOutside
            )
        )
    }
}

@Preview
@Composable
private fun PreviewMyAlertDialog() {
    MyAlertDialog(
        title = "Title",
        text = "Text",
        dismissButtonText = stringResource(android.R.string.cancel)
    )
}
