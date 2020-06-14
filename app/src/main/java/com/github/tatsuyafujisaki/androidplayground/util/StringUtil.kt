package com.github.tatsuyafujisaki.androidplayground.util

import android.text.Html

object StringUtil {
    /**
     * Decode HTML entities such as "&amp;" to "&".
     */
    fun decode(html: String) = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
}
