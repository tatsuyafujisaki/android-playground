package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@Composable
fun OutlineButtonExample(
    enabled: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        border = BorderStroke(
            width = 4.dp,
            color = Color.Red
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            disabledBackgroundColor = Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Text("Sample")
    }
}

@Preview
@Composable
private fun PreviewOutlineButtonExample(
    @PreviewParameter(BooleanProvider::class) enabled: Boolean
) {
    OutlineButtonExample(enabled) {}
}
