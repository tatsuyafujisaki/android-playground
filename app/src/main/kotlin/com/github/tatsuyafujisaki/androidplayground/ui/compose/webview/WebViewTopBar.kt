package com.github.tatsuyafujisaki.androidplayground.ui.compose.webview

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewTopBar(
    title: String,
    canGoBack: Boolean,
    canGoForward: Boolean,
    onNavigationIconClick: () -> Unit,
    onBackClick: () -> Unit,
    onReloadClick: () -> Unit,
    onForwardClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier.clickable(onClick = onNavigationIconClick),
            )
        },
        actions = {
            IconButton(
                onClick = onBackClick,
                enabled = canGoBack,
            ) {
                Icon(
                    imageVector = Filled.ArrowBack,
                    contentDescription = null,
                    tint = if (canGoBack) Color.Black else Color.Gray,
                )
            }
            IconButton(onReloadClick) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.Black,
                )
            }
            IconButton(
                onClick = onForwardClick,
                enabled = canGoForward,
            ) {
                Icon(
                    imageVector = Filled.ArrowForward,
                    contentDescription = null,
                    tint = if (canGoForward) Color.Black else Color.Gray,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun WebViewTopBarPreview() {
    WebViewTopBar(
        title = "Title",
        canGoBack = true,
        canGoForward = false,
        onNavigationIconClick = {},
        onBackClick = {},
        onReloadClick = {},
        onForwardClick = {},
    )
}
