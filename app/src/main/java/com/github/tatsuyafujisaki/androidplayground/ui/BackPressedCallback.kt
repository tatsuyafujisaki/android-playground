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
                    .addCallback(fragment.viewLifecycleOwner) {
                        onBackPressed()
                    }
            }
        }
    }
}
