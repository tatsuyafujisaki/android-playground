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
     * "https://sub.example.com/foo".toHttpUrl() -> "https://sub.example.com/"
     */
    fun getPathlessUrl(url: HttpUrl) =
        HttpUrl.Builder().scheme(url.scheme).host(url.host).build()

    /**
     * "https://sub.example.com/foo" -> "sub.example.com"
     */
    fun getHost(url: String) = url.toUri().host

    /**
     * "https://sub.example.com/foo" -> "example.com"
     */
    fun getDomain(url: String) = url.toHttpUrlOrNull()?.topPrivateDomain()
}
