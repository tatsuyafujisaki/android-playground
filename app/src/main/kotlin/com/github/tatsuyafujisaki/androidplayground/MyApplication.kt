package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import android.os.StrictMode
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Firebase.appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )

        if (BuildConfig.DEBUG) {
            // https://developer.android.com/guide/app-compatibility/restrictions-non-sdk-interfaces#test-strictmode-api
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectNonSdkApiUsage()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }
}
