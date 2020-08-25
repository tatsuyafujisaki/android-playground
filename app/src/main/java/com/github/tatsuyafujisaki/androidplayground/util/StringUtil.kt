package com.github.tatsuyafujisaki.androidplayground.util

import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import androidx.annotation.ColorInt

object StringUtil {
    fun isValidEmailAddress(emailAddress: String) =
        Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()

    fun isValidPhoneNumber(phoneNumber: String) =
        Patterns.PHONE.matcher(phoneNumber).matches()

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
