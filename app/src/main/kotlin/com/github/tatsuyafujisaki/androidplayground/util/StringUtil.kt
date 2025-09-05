package com.github.tatsuyafujisaki.androidplayground.util

import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import androidx.annotation.ColorInt
import androidx.core.text.htmlEncode

@Suppress("unused")
object StringUtil {
    fun isValidEmailAddress(emailAddress: String) =
        Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()

    fun isValidPhoneNumber(phoneNumber: String) = Patterns.PHONE.matcher(phoneNumber).matches()

    /**
     * Decodes HTML entities. For encoding HTML entities, use [htmlEncode].
     */
    fun decode(source: String) = Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT).toString()

    fun getColoredSpannableString(
        source: String,
        @ColorInt color: Int,
    ) = SpannableString(source).apply {
        setSpan(
            ForegroundColorSpan(color),
            0,
            length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
    }

    /**
     * https://developer.android.com/develop/ui/compose/navigation#optional-args
     */
    fun createNavigationComposeQueryParameters(vararg args: String) =
        if (args.isEmpty()) "" else args.joinToString(separator = "&", prefix = "?") { "$it={$it}" }
}
