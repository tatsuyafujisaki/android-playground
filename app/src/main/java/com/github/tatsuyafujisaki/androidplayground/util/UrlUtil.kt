package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.net.toUri
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

object UrlUtil {
    /**
     * Redundant explanatory wrapper. Unnecessary in practice.
     */
    fun createHttpUrl(url: String) = url.toHttpUrlOrNull()

    /**
     * "https://foo.example.com/bar".toHttpUrl() -> "https://foo.example.com/"
     */
    fun getPathlessUrl(url: HttpUrl) =
        HttpUrl.Builder().scheme(url.scheme).host(url.host).build()

    /**
     * "https://foo.example.com/bar" -> "foo.example.com"
     */
    fun getHost(url: String) = url.toUri().host

    /**
     * "https://foo.example.com/bar" -> "example.com"
     */
    fun getDomain(url: String) = url.toHttpUrlOrNull()?.topPrivateDomain()
}
