package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Patterns

@Suppress("unused")
object StringUtil {
    fun isValidEmailAddress(emailAddress: String) =
        Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()

    fun isValidPhoneNumber(phoneNumber: String) = Patterns.PHONE.matcher(phoneNumber).matches()

    /**
     * https://developer.android.com/develop/ui/compose/navigation#optional-args
     */
    fun createNavigationComposeQueryParameters(vararg args: String) =
        if (args.isEmpty()) "" else args.joinToString(separator = "&", prefix = "?") { "$it={$it}" }
}
