package com.github.tatsuyafujisaki.androidplayground.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

fun generateLoremIpsum() = LoremIpsum().values.joinToString()
fun generateLoremIpsum(words: Int) = LoremIpsum(words).values.joinToString()
