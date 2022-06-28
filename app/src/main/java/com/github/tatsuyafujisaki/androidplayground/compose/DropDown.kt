package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> DropdownMenu(
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
                    onClick = {
                        onClick(option)
                        // Close the menu when you click the menu item.
                        expanded = false
                    }
                ) {
                    Text(option.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropdownMenu() {
    DropdownMenu(
        options = listOf("Bacon", "Lettuce", "Tomato"),
        selectedValue = "Lettuce",
        onClick = {}
    )
}
