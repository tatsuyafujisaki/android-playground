package com.github.tatsuyafujisaki.androidplayground.util

import android.text.util.Linkify
import android.widget.TextView

/**
 * Impractical redundant explanatory wrappers
 */
@Suppress("unused")
object LinkifyUtil {
    /**
     * Equivalent to android:autoLink="email|phone|web" in XML
     */
    fun TextView.addLink() {
        autoLinkMask = Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS

        // Alternatively
        Linkify.addLinks(this, Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS)
    }

    /**
     * "tel:" is to initiate a phone call.
     * https://developer.android.com/guide/components/intents-common#DialPhone
     */
    fun TextView.addJapanesePhoneNumberLink() {
        Linkify.addLinks(this, """0\d{9,10}""".toPattern(), "tel:")
    }
}
