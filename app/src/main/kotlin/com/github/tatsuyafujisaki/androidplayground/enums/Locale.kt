package com.github.tatsuyafujisaki.androidplayground.enums

import java.util.Locale

@Suppress("unused")
enum class Locale {
    ENGLISH,
    JAPANESE,
    UNKNOWN,
    ;

    companion object {
        fun create() =
            when (Locale.getDefault().language) {
                "en" -> ENGLISH
                "ja" -> JAPANESE
                else -> UNKNOWN
            }
    }
}
