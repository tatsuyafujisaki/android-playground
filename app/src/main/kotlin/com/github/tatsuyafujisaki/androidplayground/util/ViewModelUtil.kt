package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

@Suppress("unused")
object ViewModelUtil {
    /**
     * https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
     */
    inline fun <reified VM : ViewModel> createViewModelProviderFactory(crossinline initializer: CreationExtras.() -> VM) =
        viewModelFactory {
            initializer {
                initializer()
            }
        }
}
