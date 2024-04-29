package com.github.tatsuyafujisaki.androidplayground.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class FragmentFactoryFactory<F : Fragment>(private val instantiate: () -> F) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return instantiate()
    }
}
