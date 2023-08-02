package com.github.tatsuyafujisaki.androidplayground.util

import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

object FragmentUtil {
    object DialogUtil {
        fun isDialogShowing(fragmentManager: FragmentManager, tag: String) =
            (fragmentManager.findFragmentByTag(tag) as? DialogFragment)?.dialog?.isShowing
    }

    private val FragmentManager.fragmentNames
        get() = fragments.map { it.javaClass.simpleName }

    private val FragmentManager.backStackEntryNames
        get() = (0 ..< backStackEntryCount)
            .map {
                val tag = getBackStackEntryAt(it).name
                /**
                 * [FragmentManager.findFragmentByTag returns null
                 *   if the [BackStackEntry] has NOT been added with the tag.
                 */
                findFragmentByTag(tag)?.javaClass?.simpleName ?: tag
            }

    fun keepScreenOn(fragment: Fragment) {
        val window = fragment.requireActivity().window
        fragment.viewLifecycleOwner.lifecycle.addObserver(
            object : DefaultLifecycleObserver {
                override fun onResume(owner: LifecycleOwner) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }

                override fun onPause(owner: LifecycleOwner) {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }
        )
    }

    fun FragmentManager.popBackStack(fragmentCountToPop: Int) {
        popBackStack(
            getBackStackEntryAt(backStackEntryCount - fragmentCountToPop).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}
