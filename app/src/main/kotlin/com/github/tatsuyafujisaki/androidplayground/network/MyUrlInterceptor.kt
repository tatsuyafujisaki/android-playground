package com.github.tatsuyafujisaki.androidplayground.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class MyUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url

        Log.d("👀request", request.toString())
        Log.d("👀request > url ", httpUrl.toString())
        Log.d("👀request > url > encodedPath", httpUrl.encodedPath)
        Log.d("👀request > url > pathSegments", httpUrl.pathSegments.toString())
        Log.d(
            "👀request > url > encodedPathSegments",
            httpUrl.encodedPathSegments.toString(),
        )
        Log.d("👀request > url > encodedQuery", httpUrl.encodedQuery.toString())
        Log.d(
            "👀request > url > queryParameterNames",
            httpUrl.queryParameterNames.toString(),
        )
        Log.d(
            "👀request > url > queryParameterValues",
            httpUrl.queryParameterNames.flatMap(httpUrl::queryParameterValues).toString(),
        )
        Log.d("👀request > method", request.method)
        Log.d("👀request > headers", request.headers.toString())
        Log.d("👀request > body", request.body.toString())
        Log.d("👀request > tag", request.tag().toString())

        val response = chain.proceed(request)

        Log.d("👀response", response.toString())
        Log.d("👀response > protocol", response.protocol.toString())
        Log.d("👀response > message", response.message)
        Log.d("👀response > code", response.code.toString())
        Log.d("👀response > headers", response.headers.toString())

        // Don't call "response.body?.string()"
        // because "response.body?.string()" can be called only once
        // and calling it twice will throw "java.lang.IllegalStateException: closed".
        // https://square.github.io/okhttp/5.x/okhttp/okhttp3/-response-body/string.html
        Log.d("👀response > body", response.peekBody(Long.MAX_VALUE).string())

        return response
    }
}
