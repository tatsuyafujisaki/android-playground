package com.github.tatsuyafujisaki.androidplayground.di

import com.github.tatsuyafujisaki.androidplayground.MainApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectBuilder::class,
    ApplicationModule::class
])
interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory: AndroidInjector.Factory<MainApplication>
}
