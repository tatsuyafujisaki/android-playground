package com.github.tatsuyafujisaki.androidplayground.di

import com.github.tatsuyafujisaki.androidplayground.ui.activity.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
