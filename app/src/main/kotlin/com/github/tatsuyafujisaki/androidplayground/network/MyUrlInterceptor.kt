package com.github.tatsuyafujisaki.androidplayground.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class MyUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url

        Log.d("👀httpUrl", httpUrl.toString())
        Log.d("👀encodedPath", httpUrl.encodedPath)
        Log.d("👀pathSegments", httpUrl.pathSegments.toString())
        Log.d("👀encodedPathSegments", httpUrl.encodedPathSegments.toString())
        Log.d("👀encodedQuery", httpUrl.encodedQuery.toString())
        Log.d("👀queryParameterNames", httpUrl.queryParameterNames.toString())
        Log.d(
            "👀queryParameterValues",
            httpUrl.queryParameterNames.flatMap(httpUrl::queryParameterValues).toString(),
        )

        return chain.proceed(request)
    }
}
