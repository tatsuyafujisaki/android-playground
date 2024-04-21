package com.github.tatsuyafujisaki.androidplayground.util

import com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.ENGLISH
import com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.JAPANESE
import com.github.tatsuyafujisaki.androidplayground.data.enum.Locale.UNKNOWN
import java.util.Locale

object LocaleUtil {
    val locale
        get() = when (Locale.getDefault().language) {
            "en" -> ENGLISH
            "ja" -> JAPANESE
            else -> UNKNOWN
        }
}
