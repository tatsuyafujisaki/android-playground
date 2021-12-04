package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.network.BuildConfig
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader

// @HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            with(AndroidFlipperClient.getInstance(this)) {
                addPlugin(NetworkFlipperPlugin())
                start()
            }
        }
    }
}
