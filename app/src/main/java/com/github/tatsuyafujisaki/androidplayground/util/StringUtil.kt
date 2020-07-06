package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

object StringUtil {
    /**
     * Decode HTML entities such as "&amp;" to "&".
     */
    fun decode(html: String) = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
    
    fun getSpannedString(s: String, @ColorInt color: Int) =
        SpannableString(s).apply {
            setSpan(
                ForegroundColorSpan(color),
                0,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

    fun getSpannedString(context: Context, s: String, @ColorRes color: Int) =
        getSpannedString(s, ContextCompat.getColor(context, color))
}
