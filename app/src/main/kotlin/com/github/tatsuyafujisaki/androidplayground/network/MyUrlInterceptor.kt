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

        chain.proceed(request).also {
            Log.d("👀response", it.toString())
            Log.d("👀response > code", it.code.toString())
            Log.d("👀response > message", it.message)
            Log.d("👀response > headers", it.headers.toString())
            Log.d("👀response > body", it.body?.string().toString())
            Log.d("👀response > networkResponse", it.networkResponse.toString())
        }

        return chain.proceed(request)
    }
}
