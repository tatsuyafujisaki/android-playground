package com.github.tatsuyafujisaki.androidplayground

class MainApplication : dagger.android.DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.factory().create(this)
}