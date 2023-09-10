package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

@SuppressWarnings("unused")
object EmailUtil {
    fun Activity.composeEmailWithNoAttachment(
        to: Array<String>,
        subject: String,
        body: String,
    ) {
        /**
         * [Intent.ACTION_SENDTO] for no attachment.
         */
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri() // filters only email apps.
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun Activity.composeEmailWithOneAttachment(
        to: Array<String>,
        subject: String,
        body: String,
        attachment: Uri,
    ) {
        /**
         * [Intent.ACTION_SEND] for one attachment.
         */
        val intent = Intent(Intent.ACTION_SEND).apply {
            data = "mailto:".toUri() // filters only email apps.
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun Activity.composeEmailWithMultipleAttachments(
        to: Array<String>,
        subject: String,
        body: String,
        attachments: Array<Uri>,
    ) {
        /**
         * [Intent.ACTION_SEND_MULTIPLE] is for multiple attachments.
         */
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
            data = "mailto:".toUri() // filters only email apps.
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            putExtra(Intent.EXTRA_STREAM, attachments)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
