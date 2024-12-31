package com.github.tatsuyafujisaki.androidplayground.ui.compose.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun SingleChoiceSegmentedButtonRowPreview() {
    val options =
        listOf(
            Icons.Default.Favorite to "Heart",
            Icons.Default.MusicNote to "Music",
            Icons.Default.Star to "Star",
        )
    var selectedIndex by remember { mutableIntStateOf(0) }
    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, (imageVector, text) ->
            SegmentedButton(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {
                    SegmentedButtonDefaults.Icon(active = selectedIndex == index) {
                        Icon(
                            imageVector = imageVector,
                            contentDescription = null,
                            // Without the following, the icon will wobble when you tap it.
                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                        )
                    }
                },
                label = {
                    Text(text = text)
                },
            )
        }
    }
}
