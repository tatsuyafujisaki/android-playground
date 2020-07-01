package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

object FragmentUtil {
    fun logFragmentManagers(fragment: Fragment) {
        fragment.run {
            val fragmentName = fragment.javaClass.simpleName
            logFragmentManager("$fragmentName.parentFragmentManager", parentFragmentManager)
            logFragmentManager("$fragmentName.childFragmentManager", childFragmentManager)
        }
    }

    private fun logFragmentManager(tagPrefix: String, fragmentManager: FragmentManager) {
        return fragmentManager.let {
            Log.d("$tagPrefix.fragments", getFragmentNames(it)
                .joinToString(",").run { if (isEmpty()) "(empty)" else this })
            Log.d("$tagPrefix.backStackEntries", getBackStackEntryNames(it)
                .joinToString(",").run { if (isEmpty()) "(empty)" else this })
        }
    }

    private fun getFragmentNames(fragmentManager: FragmentManager) =
        fragmentManager.fragments.map { it.javaClass.simpleName }

    private fun getBackStackEntryNames(fragmentManager: FragmentManager) =
        (0 until fragmentManager.backStackEntryCount)
            .map {
                val tag = fragmentManager.getBackStackEntryAt(it).name
                /**
                 * findFragmentByTag(String) returns null
                 * if the BackStackEntry was NOT added with the tag.
                 */
                fragmentManager.findFragmentByTag(tag)?.javaClass?.simpleName ?: tag
            }

    fun getNavHostFragments(fragmentManager: FragmentManager) =
        fragmentManager.fragments.filterIsInstance(NavHostFragment::class.java)

    /**
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun getNavHostFragment(fragmentManager: FragmentManager, @IdRes navHostFragmentId: Int) =
        fragmentManager.findFragmentById(navHostFragmentId) as? NavHostFragment

    // Redundant explanatory wrapper. Unnecessary in practice.
    fun getNavController(activity: Activity, @IdRes navHostFragmentId: Int) =
        activity.findNavController(navHostFragmentId)

    fun getCurrentFragment(navHostFragment: NavHostFragment) =
        navHostFragment
            .childFragmentManager
            .primaryNavigationFragment

    fun canNavigateUp(navController: NavController) =
        navController.graph.startDestination != navController.currentDestination?.id

}
