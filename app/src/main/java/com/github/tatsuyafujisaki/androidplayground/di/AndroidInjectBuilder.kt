package com.github.tatsuyafujisaki.androidplayground.di

import com.github.tatsuyafujisaki.androidplayground.HomeFragment
import com.github.tatsuyafujisaki.androidplayground.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidInjectBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}
