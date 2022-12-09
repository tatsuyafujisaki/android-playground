package com.github.tatsuyafujisaki.androidplayground.ui

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class BackPressedCallback(fragment: Fragment, onBackPressed: (OnBackPressedCallback) -> Unit) {
    init {
        fragment.lifecycleScope.launch {
            fragment.repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragment
                    .requireActivity()
                    .onBackPressedDispatcher
                    // Even if the same callback is added more than once, only one of the same callbacks will be invoked.
                    .addCallback(fragment.viewLifecycleOwner) {
                        onBackPressed(this)
                    }
            }
        }
    }
}
