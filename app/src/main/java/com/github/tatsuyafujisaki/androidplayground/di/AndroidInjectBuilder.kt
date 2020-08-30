package com.github.tatsuyafujisaki.androidplayground.di

import com.github.tatsuyafujisaki.androidplayground.ui.activity.MainActivity
import com.github.tatsuyafujisaki.androidplayground.ui.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidInjectBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindHomeFragment(): HomeFragment
}
