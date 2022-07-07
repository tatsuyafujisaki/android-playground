package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyAlertDialog(
    text: String,
    title: String? = null,
    confirmButtonText: String = stringResource(android.R.string.ok),
    dismissButtonText: String? = null,
    canDismiss: Boolean = true,
    onConfirmButtonClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        AlertDialog(
            onDismissRequest = {
                // You clicked outside the dialog or the back button.
                if (canDismiss) {
                    onDismiss()
                    isVisible = false
                }
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
            modifier = Modifier.shadow(24.dp),
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
            }
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
