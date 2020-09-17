package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.net.toUri
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Impractical redundant explanatory wrappers
 */
object UrlUtil {
    fun createHttpUrl(url: String) = url.toHttpUrlOrNull()

    fun getPathlessUrl(url: HttpUrl) =
        HttpUrl.Builder().scheme(url.scheme).host(url.host).build()

    fun getHost(url: String) = url.toUri().host

    fun getDomain(url: String) = url.toHttpUrlOrNull()?.topPrivateDomain()
}
