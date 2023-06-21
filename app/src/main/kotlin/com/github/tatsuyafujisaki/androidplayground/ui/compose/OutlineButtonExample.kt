package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.BooleanProvider

@Composable
fun OutlinedButtonExample(
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
            containerColor = Color.White,
            disabledContainerColor = Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Text("Sample")
    }
}

@Preview
@Composable
private fun OutlineButtonExamplePreview(
    @PreviewParameter(BooleanProvider::class) enabled: Boolean
) {
    OutlinedButtonExample(enabled) {}
}
