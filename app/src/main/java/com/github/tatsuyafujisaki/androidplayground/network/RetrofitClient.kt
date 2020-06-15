package com.github.tatsuyafujisaki.androidplayground.network

import android.os.Build
import android.webkit.CookieManager
import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.okHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val cookieManager = CookieManager.getInstance()

    val okHttpBuilder: OkHttpClient.Builder =
        OkHttpClient
            .Builder()
            .addInterceptor {
                it.proceed(
                    it
                        .request()
                        .newBuilder()
                        // Each header added by an interceptor can be overwritten
                        // by passing the same header when calling an API.
                        .addHeader("X-BuildManufacturer", Build.MANUFACTURER)
                        .addHeader("X-Brand", Build.BRAND)
                        .addHeader("X-Product", Build.PRODUCT)
                        .addHeader("X-BuildModel", Build.MODEL)
                        // Android OS version
                        .addHeader("X-BuildVersionRelease", Build.VERSION.RELEASE)
                        .addHeader("X-VersionName", BuildConfig.VERSION_NAME)
                        .build()
                )
            }
            .cookieJar(object : CookieJar {
                // Set cookies in CookieManger to HTTP requests
                override fun loadForRequest(url: HttpUrl): List<Cookie> =
                    cookieManager
                        .getCookie(url.toString())
                        ?.split(';')
                        ?.filter {
                            isKeyOfInterest(it.split('=').firstOrNull()?.trim())
                        }
                        ?.mapNotNull {
                            Cookie.parse(getBaseUrl(url), it.trim() /* key=value */)
                        }.orEmpty()

                // Save cookies in HTTP responses to CookieManger
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    cookies.filter {
                        isKeyOfInterest(it.name)
                    }.forEach {
                        cookieManager.setCookie(url.host, "${it.name}=${it.value}")
                    }
                }
            })

    val googleApiService: GoogleApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(GoogleApiService::class.java)

    private fun isKeyOfInterest(cookieKey: String?) =
        setOf("SampleCookieKey1", "SampleCookieKey1").contains(cookieKey)

    private fun getBaseUrl(url: HttpUrl) =
        HttpUrl.Builder().scheme(url.scheme).host(url.host).build()
}
