package com.github.tatsuyafujisaki.androidplayground.sample

import kotlin.random.Random

@Suppress("unused")
object RandomImage {
    fun getUrl(sizeInPixel: Int = 1_000) =
        "https://picsum.photos/$sizeInPixel?random=${Random.nextInt()}"

    fun getUrl(widthInPixel: Int, heightInPixel: Int) =
        "https://picsum.photos/$widthInPixel/$heightInPixel?random=${Random.nextInt()}"
}
