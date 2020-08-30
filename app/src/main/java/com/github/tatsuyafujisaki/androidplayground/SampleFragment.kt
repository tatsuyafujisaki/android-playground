package com.github.tatsuyafujisaki.androidplayground

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SampleFragment: Fragment() {
    companion object {
        private const val FOO_KEY = "foo"
        private const val BAR_KEY = "bar"

        // Old school best practice (factory method pattern) of creating a Fragment before the Navigation era.
        fun newInstance(foo: String, bar: String) = SampleFragment().apply {
            arguments = bundleOf(
                FOO_KEY to foo,
                BAR_KEY to bar
            )
        }
    }
}