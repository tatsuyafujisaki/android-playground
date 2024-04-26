package com.github.tatsuyafujisaki.androidplayground.network

import java.time.LocalDateTime
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

// This is reinventing the wheel. It is recommended to use OkHttp's built-in cache instead.
// https://square.github.io/okhttp/recipes/#response-caching-kt-java
class CacheInterceptor(
    private val cache: Set<Triple<String, String, LocalDateTime>>,
    private val upsert: (String, String) -> Unit
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val response = chain.proceed(request)
        val cache = cache.filter { (cachedUrl, responseJson, createdAt) ->
            cachedUrl == url && isLessThan1HoursOld(createdAt)
        }.map { (_, responseJson, _) ->
            responseJson
        }.firstOrNull()

        return if (cache != null) {
            response.newBuilder().body(cache.toResponseBody()).build()
        } else {
            upsert(url, response.peekBody(Long.MAX_VALUE).string())
            response
        }
    }

    private fun isLessThan1HoursOld(localDateTime: LocalDateTime) =
        localDateTime.isAfter(LocalDateTime.now().minusHours(1))
}
