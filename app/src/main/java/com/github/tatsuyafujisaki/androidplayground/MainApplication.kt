package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.BuildConfig
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho

// @HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            with(AndroidFlipperClient.getInstance(this)) {
                addPlugin(
                    InspectorFlipperPlugin(
                        this@MainApplication,
                        DescriptorMapping.withDefaults()
                    )
                )
                addPlugin(networkFlipperPlugin)
                start()
            }
        }
    }

    companion object {
        val networkFlipperPlugin = NetworkFlipperPlugin()
    }
}
