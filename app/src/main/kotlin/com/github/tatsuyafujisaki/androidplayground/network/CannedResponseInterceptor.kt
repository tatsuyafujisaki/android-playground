package com.github.tatsuyafujisaki.androidplayground.network

import java.time.LocalDateTime
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

@Suppress("unused")
class CannedResponseInterceptor(
    private val cache: Set<Triple<String, String, LocalDateTime>>,
    private val upsert: (String, String) -> Unit,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        fun shouldReturnCannedResponse(url: String): Boolean {
            return url.contains("/foo/bar?baz=qux")
        }

        return if (shouldReturnCannedResponse(request.url.toString())) {
            val cannedResponse = """
                { "statusCode":"500" }
                """.trimIndent()

            Response
                .Builder()
                .request(request)
                .protocol(Protocol.HTTP_2) // You must specify a protocol or the "java.lang.IllegalStateException: protocol == null" error will be thrown.
                .code(200) // You must specify a code or the "java.lang.IllegalStateException: code < 0: -1" error will be thrown.
                .message("") // You must specify a message or the "java.lang.IllegalStateException: message == null" will be thrown.
                .body(cannedResponse.toResponseBody()).build()
        } else {
            chain.proceed(request)
        }
    }
}
