package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

object EmailUtil {
    fun Activity.composeEmailWithNoAttachment(
        to: Array<String>,
        subject: String,
        body: String
    ) {
        val intent = Intent(Intent.ACTION_SENDTO).apply { // ACTION_SENDTO is for no attachment.
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
        attachment: Uri
    ) {
        val intent = Intent(Intent.ACTION_SEND).apply { // ACTION_SEND is for one attachment.
            data = "mailto:".toUri() // filters only email apps.
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body),
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
        attachments: Array<Uri>
    ) {
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply { // ACTION_SEND_MULTIPLE is for multiple attachments.
            data = "mailto:".toUri() // filters only email apps.
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body),
            putExtra(Intent.EXTRA_STREAM, attachments)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
