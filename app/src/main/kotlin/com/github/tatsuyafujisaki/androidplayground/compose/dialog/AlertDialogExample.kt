package com.github.tatsuyafujisaki.androidplayground.compose.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String? = null,
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
        text = text?.let {
            {
                Text(it)
            }
        }
    )
}

@Preview
@Composable
private fun AlertDialogExamplePreview() {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        AlertDialogExample(
            title = "Title",
            text = "Text",
            dismissButtonText = stringResource(android.R.string.cancel),
            onDismissRequest = { isVisible = false }
        )
    }
}
