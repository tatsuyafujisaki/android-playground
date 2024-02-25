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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.HorizontalDivider
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
fun <T> ExpandableList(
    items: List<T>,
    collapsedListItemContent: @Composable ColumnScope.(T, Boolean) -> Unit,
    expandedListItemContent: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(items = items) { index, item ->
            var isExpanded by remember { mutableStateOf(false) }
            ExpandableListItem(
                isExpanded = isExpanded,
                onExpansionChange = { isExpanded = !isExpanded },
                collapsedContent = {
                    collapsedListItemContent(item, isExpanded)
                },
                expandedContent = { expandedListItemContent(item) },
            )
            if (index < items.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ExpandableListItem(
    isExpanded: Boolean,
    onExpansionChange: () -> Unit = {},
    collapsedContent: @Composable ColumnScope.() -> Unit,
    expandedContent: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                // Disables a touch ripple.
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onExpansionChange,
            ),
    ) {
        collapsedContent()
        AnimatedVisibility(visible = isExpanded, content = expandedContent)
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
        collapsedListItemContent = { item, isExpanded ->
            val iconDegrees by animateFloatAsState(
                targetValue = if (isExpanded) 180f else 0f,
                label = "",
            )
            Row(
                modifier = Modifier.padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Image(
                    // Requires androidx.compose.material:material-icons-extended.
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.rotate(iconDegrees),
                )
            }
        },
        expandedListItemContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(all = 16.dp),
            ) {
                Text(
                    text = it.body,
                )
            }
        }
    )
}
