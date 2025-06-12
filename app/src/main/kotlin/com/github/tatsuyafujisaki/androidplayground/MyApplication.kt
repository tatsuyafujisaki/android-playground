package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.Executors

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // https://developer.android.com/guide/app-compatibility/restrictions-non-sdk-interfaces#test-strictmode-api
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder().detectNonSdkApiUsage().penaltyLog()
                    .penaltyListener(Executors.newSingleThreadExecutor()) {
                        Log.e("StrictMode", "Non-SDK interface: $it")
                    }.build()
            )
        }
    }
}
