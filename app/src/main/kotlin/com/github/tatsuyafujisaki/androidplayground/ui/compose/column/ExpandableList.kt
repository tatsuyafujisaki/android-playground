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
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private data class MyData(
    val title: String,
    val body: String,
    var expanded: Boolean = false,
)

@Composable
fun <T> ExpandableList(
    items: List<T>,
    collapsedListItemContent: @Composable ColumnScope.(T, Boolean) -> Unit,
    expandedListItemContent: @Composable AnimatedVisibilityScope.(T) -> Unit,
    modifier: Modifier = Modifier,
    divider: @Composable () -> Unit = {},
    bottomItemContent: @Composable LazyItemScope.() -> Unit = {},
    onExpansionChange: (T, Boolean) -> Unit = { _, _ -> },
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        itemsIndexed(items = items) { index, item ->
            // Don't use "remember" because it gets reset when you scroll away.
            var expanded by rememberSaveable { mutableStateOf(false) }
            ExpandableListItem(
                expanded = expanded,
                onExpansionChange = {
                    onExpansionChange(item, expanded)
                    expanded = !expanded
                },
                collapsedContent = {
                    collapsedListItemContent(item, expanded)
                },
                expandedContent = { expandedListItemContent(item) },
            )
            if (index < items.lastIndex) {
                divider()
            }
        }
        item(content = bottomItemContent)
    }
}

@Composable
private fun ExpandableListItem(
    expanded: Boolean,
    collapsedContent: @Composable ColumnScope.() -> Unit,
    expandedContent: @Composable AnimatedVisibilityScope.() -> Unit,
    onExpansionChange: () -> Unit = {},
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
        AnimatedVisibility(visible = expanded, content = expandedContent)
    }
}

@Preview
@Composable
private fun ExpandableListPreview() {
    ExpandableList(
        items = List(100) {
            MyData(
                title = "Title $it",
                body = "Hello world",
            )
        },
        collapsedListItemContent = { item, expanded ->
            val iconDegrees by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
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
        },
        divider = {
            HorizontalDivider()
        },
    )
}
