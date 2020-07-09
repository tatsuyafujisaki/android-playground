package com.github.tatsuyafujisaki.androidplayground.util

import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

object StringUtil {
    /**
     * Decode HTML entities such as "&amp;" to "&".
     */
    fun decode(html: String) = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
    
    fun String.color(@ColorInt color: Int) =
        SpannableString(this).apply {
            setSpan(
                ForegroundColorSpan(color),
                0,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
}
