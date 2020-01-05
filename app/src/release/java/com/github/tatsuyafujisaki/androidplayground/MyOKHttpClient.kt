package com.github.tatsuyafujisaki.androidplayground

import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient

val okHttpClient = RetrofitClient.okHttpBuilder.build()