package com.github.tatsuyafujisaki.androidplayground.network

import java.time.LocalDateTime
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

// Consider using OkHttp's built-in cache, instead.
// https://square.github.io/okhttp/recipes/#response-caching-kt-java
@Suppress("unused")
class CacheInterceptor(
    private val cache: Set<Triple<String, String, LocalDateTime>>,
    private val upsert: (String, String) -> Unit,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val cache =
            cache.filter { (cachedUrl, _, createdAt) ->
                cachedUrl == url && isLessThan1HoursOld(createdAt)
            }.map { (_, responseJson, _) ->
                responseJson
            }.firstOrNull()

        return if (cache != null) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_2) // You must specify a protocol or the "java.lang.IllegalStateException: protocol == null" error will be thrown.
                .code(200) // You must specify a code or the "java.lang.IllegalStateException: code < 0: -1" error will be thrown.
                .message("") // You must specify a message or the "java.lang.IllegalStateException: message == null" will be thrown.
                .body(cache.toResponseBody())
                .build()
        } else {
            chain.proceed(request).apply {
                if (isSuccessful) {
                    upsert(url, peekBody(Long.MAX_VALUE).string())
                }
            }
        }
    }

    private fun isLessThan1HoursOld(localDateTime: LocalDateTime) =
        localDateTime.isAfter(LocalDateTime.now().minusHours(1))
}
