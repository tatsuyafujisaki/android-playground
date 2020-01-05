package com.github.tatsuyafujisaki.androidplayground

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.BuildConfig
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).run {
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

    override fun applicationInjector() = DaggerAppComponent.factory().create(this)

    companion object {
        val networkFlipperPlugin = NetworkFlipperPlugin()
    }
}
