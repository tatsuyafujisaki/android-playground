package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

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

    @Deprecated(message = "factory for view model version older than 2.5.0")
    fun <V : ViewModel, T> createViewModelProviderFactory(
        create: (T) -> V
    ): (T) -> ViewModelProvider.Factory = {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <V : ViewModel> create(modelClass: Class<V>) = create(it) as V
        }
    }
}
