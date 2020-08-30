package com.github.tatsuyafujisaki.androidplayground

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.tatsuyafujisaki.androidplayground.dataClass.SampleRepository

class SampleFragment : Fragment(R.layout.fragment_sample) {
    companion object {
        private const val FOO_KEY = "foo"
        private const val BAR_KEY = "bar"
        private const val REPOSITORY_KEY = "repository"

        // Old school best practice (factory method pattern) of creating a Fragment before the Navigation era.
        fun newInstance(foo: String, bar: String, repository: SampleRepository) =
            SampleFragment().apply {
                arguments = bundleOf(
                    FOO_KEY to foo,
                    BAR_KEY to bar,
                    REPOSITORY_KEY to repository
                )
            }
    }
}