package com.github.tatsuyafujisaki.androidplayground.ui.compose.column

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class MyData(
    val title: String,
    val body: String,
    var isExpanded: Boolean = false,
)

@Composable
fun ExpandableList(
    items: List<MyData>,
    expandedListItemContent: @Composable AnimatedVisibilityScope.(MyData) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items = items) { index, item ->
            ExpandableListItem(
                title = item.title,
                content = { expandedListItemContent(item) },
            )
            if (index < items.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ExpandableListItem(
    title: String,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val iconDegrees by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "",
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                // Disables a touch ripple.
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                isExpanded = !isExpanded
            },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                IconButton(
                    modifier = Modifier.rotate(iconDegrees),
                    onClick = { isExpanded = !isExpanded },
                ) {
                    Image(
                        // Requires androidx.compose.material:material-icons-extended.
                        imageVector = Icons.Default.ExpandMore,
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(visible = isExpanded, content = content)
        }
    }
}

@Preview
@Composable
private fun ExpandableListPreview() {
    ExpandableList(
        items = List(10) {
            MyData(
                title = "Title $it",
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            )
        },
        expandedListItemContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(
                    text = it.body,
                )
            }
        }
    )
}
