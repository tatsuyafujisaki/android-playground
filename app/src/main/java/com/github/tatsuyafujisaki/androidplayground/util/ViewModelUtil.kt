package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelUtil {
    fun <V : ViewModel, T> createViewModelProviderFactory(
        create: (T) -> V
    ): (T) -> ViewModelProvider.Factory = {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <V : ViewModel> create(modelClass: Class<V>) = create(it) as V
        }
    }
}
