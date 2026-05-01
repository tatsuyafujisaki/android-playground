package com.github.tatsuyafujisaki.androidplayground.ui.compose.text.linebreak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun LineBreakExamples() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 4),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        item {
            Text(text = "LineBreak.Unspecified")
        }
        item {
            Text(text = "LineBreak.Simple")
        }
        item {
            Text(text = "LineBreak.Heading")
        }
        item {
            Text(text = "LineBreak.Paragraph")
        }
        repeat(10) { index ->
            val texts = listOf(
                generateText(s = "a", charCountPerWord = index + 1),
                generateText(s = "あ", charCountPerWord = index + 1)
            )
            texts.forEach { text ->
                item {
                    Text(
                        text = text,
                        modifier = Modifier.background(color = Color.Cyan),
                        style = TextStyle(lineBreak = LineBreak.Unspecified),
                    )
                }
                item {
                    Text(
                        text = text,
                        modifier = Modifier.background(color = Color.Magenta),
                        style = TextStyle(lineBreak = LineBreak.Simple)
                    )
                }
                item {
                    Text(
                        text = text,
                        modifier = Modifier.background(color = Color.Cyan),
                        style = TextStyle(lineBreak = LineBreak.Heading)
                    )
                }
                item {
                    Text(
                        text = text,
                        modifier = Modifier.background(color = Color.Magenta),
                        style = TextStyle(lineBreak = LineBreak.Paragraph)
                    )
                }
            }
        }
    }
}

private fun generateText(s: String, charCountPerWord: Int) =
    List(size = 10) { s.repeat(charCountPerWord) }
        .joinToString(separator = " ")
