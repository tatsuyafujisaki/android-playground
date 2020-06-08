package com.github.tatsuyafujisaki.androidplayground.util

import android.os.Build
import android.text.Html

object StringUtil {
    /**
     * Decode HTML entities such as "&amp;" to "&".
     */
    fun decode(html: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }.toString()
}