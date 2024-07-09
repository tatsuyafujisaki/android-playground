package com.github.tatsuyafujisaki.androidplayground.util

import kotlin.random.Random

object RandomImage {
    fun getUrl(sizeInPixel: Int) =
        "https://picsum.photos/$sizeInPixel?random=${Random.nextInt()}"

    fun getUrl(widthInPixel: Int, heightInPixel: Int) =
        "https://picsum.photos/$widthInPixel/$heightInPixel?random=${Random.nextInt()}"
}
