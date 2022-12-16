package com.github.tatsuyafujisaki.androidplayground.compose.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    confirmButtonText: String = stringResource(android.R.string.ok),
    dismissButtonText: String? = null,
    onConfirmButtonClick: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmButtonClick()
                    onDismissRequest()
                }
            ) {
                Text(confirmButtonText)
            }
        },
        modifier = modifier,
        dismissButton = dismissButtonText?.let {
            {
                TextButton(onClick = onDismissRequest) {
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

@Preview
@Composable
private fun MyAlertDialogPreview() {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        MyAlertDialog(
            title = "Title",
            text = "Text",
            dismissButtonText = stringResource(android.R.string.cancel),
            onDismissRequest = { isVisible = false }
        )
    }
}
