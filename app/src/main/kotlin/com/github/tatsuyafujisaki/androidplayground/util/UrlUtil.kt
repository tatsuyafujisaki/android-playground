package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.net.toUri
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Impractical redundant explanatory wrappers
 */
object UrlUtil {
    val String.httpUrl
        get() = toHttpUrlOrNull()

    val HttpUrl.pathlessUrl
        get() = HttpUrl.Builder().scheme(scheme).host(host).build()

    val String.host
        get() = toUri().host

    val String.domain
        get() = toHttpUrlOrNull()?.topPrivateDomain()
}
