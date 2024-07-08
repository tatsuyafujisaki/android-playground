package com.github.tatsuyafujisaki.androidplayground.util

import android.net.Uri
import androidx.core.net.toUri
import java.net.URL
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Impractical redundant explanatory wrappers
 *
 * https://developer.android.com/reference/android/net/Uri
 * https://developer.android.com/reference/java/net/URL
 * https://square.github.io/okhttp/5.x/okhttp/okhttp3/-http-url/
 */
object UrlUtil {
    object AndroidNetUri {
        fun convertToUri(url: String) = url.toUri()
        fun getHost(uri: Uri) = uri.host.orEmpty()
        fun getPath(uri: Uri) = uri.path.orEmpty()
        fun getEncodedPath(uri: Uri) = uri.encodedPath.orEmpty()
        fun getPathSegments(uri: Uri): List<String> = uri.pathSegments
        fun getQuery(uri: Uri) = uri.query.orEmpty()
        fun getEncodedQuery(uri: Uri) = uri.encodedQuery.orEmpty()
        fun getQueryParameterNames(uri: Uri): Set<String> = uri.queryParameterNames
        fun getBooleanQueryParameter(uri: Uri, key: String) =
            uri.getBooleanQueryParameter(key, false)

        /**
         * @param query e.g. "?k1=v1&k2=v2&k2=v3" or "k1=v1&k2=v2&k2=v3"
         */
        fun convertToQueryParameters(query: String): Map<String, Set<String>> {
            val queryWithoutQueryMark = query.replace("?", "")
            val uri = "?$queryWithoutQueryMark".toUri()
            return uri.queryParameterNames.associateWith {
                uri.getQueryParameters(it).toSet()
            }
        }

        fun removeQuery(uri: Uri): Uri = uri.buildUpon().clearQuery().build()
    }

    object JavaNetUrl {
        fun convertToURL(url: String) = URL(url)
        fun getHost(url: URL): String = url.host
        fun getPath(url: URL): String = url.path
        fun getQuery(url: URL): String = url.query
    }

    object OkHttpUrl {
        fun convertToHttpUrlOrNull(url: String) = url.toHttpUrlOrNull()
        fun getHost(httpUrl: HttpUrl) = httpUrl.host
        fun getEncodedPath(httpUrl: HttpUrl) = httpUrl.encodedPath
        fun getPathSegments(httpUrl: HttpUrl) = httpUrl.pathSegments
        fun getPathSize(httpUrl: HttpUrl) = httpUrl.pathSize
        fun getQuery(httpUrl: HttpUrl) = httpUrl.query.orEmpty()
        fun getQueryParameterNames(httpUrl: HttpUrl) = httpUrl.queryParameterNames
        fun getQuerySize(httpUrl: HttpUrl) = httpUrl.querySize
        fun getDomain(httpUrl: HttpUrl) = httpUrl.topPrivateDomain().orEmpty()
    }
}
