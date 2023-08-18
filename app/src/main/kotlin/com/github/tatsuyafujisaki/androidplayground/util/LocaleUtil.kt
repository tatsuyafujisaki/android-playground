package com.github.tatsuyafujisaki.androidplayground.util

import java.util.Locale

object LocaleUtil {
    val locale
        get() = when (Locale.getDefault().language) {
            "en" -> com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.ENGLISH
            "ja" -> com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.JAPANESE
            else -> com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.UNKNOWN
        }
}
