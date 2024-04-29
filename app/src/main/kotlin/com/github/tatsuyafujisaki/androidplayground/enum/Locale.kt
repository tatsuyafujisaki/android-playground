package com.github.tatsuyafujisaki.androidplayground.enum

import java.util.Locale

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
