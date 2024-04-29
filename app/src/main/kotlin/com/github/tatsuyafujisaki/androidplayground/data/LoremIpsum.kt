package com.github.tatsuyafujisaki.androidplayground.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

fun generateLoremIpsum() = LoremIpsum().values.first()

fun generateLoremIpsum(words: Int) = LoremIpsum(words).values.first()

private fun main() {
    println(generateLoremIpsum())
    println("--")
    println(generateLoremIpsum(5))
}
