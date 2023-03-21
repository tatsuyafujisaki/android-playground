package com.github.tatsuyafujisaki.androidplayground.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry

object FragmentUtil {
    object DialogUtil {
        fun isDialogShowing(fragmentManager: FragmentManager, tag: String) =
            (fragmentManager.findFragmentByTag(tag) as? DialogFragment)?.dialog?.isShowing
    }

    private val FragmentManager.fragmentNames
        get() = fragments.map { it.javaClass.simpleName }

    private val FragmentManager.backStackEntryNames
        get() = (0 until backStackEntryCount)
            .map {
                val tag = getBackStackEntryAt(it).name
                /**
                 * [FragmentManager.findFragmentByTag returns null
                 *   if the [BackStackEntry] has NOT been added with the tag.
                 */
                findFragmentByTag(tag)?.javaClass?.simpleName ?: tag
            }

    fun FragmentManager.popBackStack(fragmentCountToPop: Int) {
        popBackStack(
            getBackStackEntryAt(backStackEntryCount - fragmentCountToPop).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}
