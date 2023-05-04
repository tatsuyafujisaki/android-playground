package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenuExample(
    options: Iterable<T>,
    selectedValue: String,
    onClick: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                // Close the menu when you click outside the menu.
                expanded = false
            }
        ) {
            for (option in options) {
                DropdownMenuItem(
                    text = { Text(option.toString()) },
                    onClick = {
                        onClick(option)
                        // Close the menu when you click the menu item.
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DropdownMenuExamplePreview() {
    DropdownMenuExample(
        options = listOf("Bacon", "Lettuce", "Tomato"),
        selectedValue = "Bacon",
        onClick = {}
    )
}
