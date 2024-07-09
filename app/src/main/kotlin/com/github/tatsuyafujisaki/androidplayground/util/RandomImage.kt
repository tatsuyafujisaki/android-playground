package com.github.tatsuyafujisaki.androidplayground.util

object RandomImage {
    private var cachePreventionCounter = 0

    fun getImageUrl(widthInPixel: Int, heightInPixel: Int) =
        "https://picsum.photos/$widthInPixel/$heightInPixel?random=${cachePreventionCounter++}"

    fun getImageUrl(sizeInPixel: Int) = getImageUrl(sizeInPixel, sizeInPixel)
}
