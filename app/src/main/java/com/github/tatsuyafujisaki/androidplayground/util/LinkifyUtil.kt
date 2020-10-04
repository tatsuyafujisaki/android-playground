package com.github.tatsuyafujisaki.androidplayground.util

import android.text.util.Linkify
import android.widget.TextView

/**
 * Impractical redundant explanatory wrappers
 */
object LinkifyUtil {

    /**
     * Equivalent to android:autoLink="all" in XML
     */
    fun addLink1(textView: TextView) {
        textView.autoLinkMask = Linkify.ALL

        // Alternatively
        Linkify.addLinks(textView, Linkify.ALL)
    }

    /**
     * Equivalent to android:autoLink="email|phone|web" in XML
     */
    fun addLink2(textView: TextView) {
        textView.autoLinkMask = Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS

        // Alternatively
        // Linkify.addLinks(textView, Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS)
    }

    /**
     * "tel:" is to initiate a phone call.
     * https://developer.android.com/guide/components/intents-common#DialPhone
     */
    fun addJapanesePhoneNumberLink(textView: TextView) {
        Linkify.addLinks(textView, """0\d{9,10}""".toPattern(), "tel:")
    }
}
