package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.github.tatsuyafujisaki.androidplayground.BuildConfig

object MiscUtil {
    /**
     * https://developer.android.com/distribute/marketing-tools/linking-to-google-play#android-app
     */
    fun openGooglePlay(context: Context) {
        Intent(Intent.ACTION_VIEW).apply {
            data =
                ("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                    .toUri()
            setPackage("com.android.vending")
        }.let(context::startActivity)
    }
}
