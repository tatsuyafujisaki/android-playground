package com.github.tatsuyafujisaki.androidplayground.util

import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import androidx.annotation.ColorInt
import androidx.core.text.htmlEncode

object StringUtil {
    /**
     * Decodes HTML entities. For encoding HTML entities, use [htmlEncode].
     */
    fun String.htmlDecode() =
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()

    fun String.isValidEmailAddress() =
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun String.isValidPhoneNumber() =
        Patterns.PHONE.matcher(this).matches()

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
