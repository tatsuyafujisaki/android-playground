package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

object FragmentUtil {
    fun Fragment.clearViewModels() {
        viewModelStore.clear()
    }

    fun Fragment.logFragmentManagers() {
        fun FragmentManager.log(tagPrefix: String) {
            Log.d("$tagPrefix.fragments", getFragmentNames().joinToString(","))
            Log.d("$tagPrefix.backStackEntries", getBackStackEntryNames().joinToString(","))
        }
        val fragmentName = javaClass.simpleName
        parentFragmentManager.log("$fragmentName.parentFragmentManager")
        childFragmentManager.log("$fragmentName.childFragmentManager")
    }

    private fun FragmentManager.getFragmentNames() =
        fragments.map { it.javaClass.simpleName }

    private fun FragmentManager.getBackStackEntryNames() =
        (0 until backStackEntryCount)
            .map {
                val tag = getBackStackEntryAt(it).name
                /**
                 * [FragmentManager.findFragmentByTag] returns null if the [BackStackEntry] has NOT been added with the tag.
                 */
                findFragmentByTag(tag)?.javaClass?.simpleName ?: tag
            }

    fun FragmentManager.getNavHostFragments() =
        fragments.filterIsInstance(NavHostFragment::class.java)

    /**
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun FragmentManager.getNavHostFragment(@IdRes navHostFragmentId: Int) =
        findFragmentById(navHostFragmentId) as? NavHostFragment

    /**
     * Impractical redundant explanatory wrappers
     */
    fun Activity.getNavController(@IdRes navHostFragmentId: Int) =
        findNavController(navHostFragmentId)

    fun NavHostFragment.getCurrentFragment() = childFragmentManager.primaryNavigationFragment

    fun NavController.canNavigateUp() = graph.startDestination != currentDestination?.id

    fun Fragment.hasEnabledCallbacks() =
        requireActivity().onBackPressedDispatcher.hasEnabledCallbacks()
}
