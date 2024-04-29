package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.net.toUri
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Impractical redundant explanatory wrappers
 */
object UrlUtil {
    fun clearQuery(url: String) = url.toUri().buildUpon().clearQuery().build().toString()

    fun getHttpUrl(url: String) = url.toHttpUrlOrNull()

    fun getDomain(url: String) = url.toHttpUrlOrNull()?.topPrivateDomain()

    fun getPathlessUrl(url: String): HttpUrl? =
        url.toHttpUrlOrNull()?.run {
            HttpUrl.Builder().scheme(scheme).host(host).build()
        }
}
