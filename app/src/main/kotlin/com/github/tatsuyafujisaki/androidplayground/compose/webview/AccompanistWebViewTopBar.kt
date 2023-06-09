package com.github.tatsuyafujisaki.androidplayground.compose.webview

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
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
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.rememberWebViewNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccompanistWebViewTopBar(
    title: String,
    navigator: WebViewNavigator = rememberWebViewNavigator(),
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable(onClick = onNavigationIconClick)
            )
        },
        actions = {
            IconButton(
                onClick = navigator::navigateBack,
                enabled = navigator.canGoBack
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null,
                    tint = if (navigator.canGoBack) Color.Black else Color.Gray
                )
            }
            IconButton(navigator::reload) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = navigator::navigateForward,
                enabled = navigator.canGoForward
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = null,
                    tint = if (navigator.canGoForward) Color.Black else Color.Gray
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AccompanistWebViewTopBarPreview() {
    AccompanistWebViewTopBar(
        title = "Title".repeat(5),
        navigator = rememberWebViewNavigator(),
        onNavigationIconClick = {}
    )
}
