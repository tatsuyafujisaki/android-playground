package com.github.tatsuyafujisaki.androidplayground.util

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

object NavigationUtil {
    val FragmentManager.navHostFragments
        get() = fragments.filterIsInstance<NavHostFragment>()

    val NavHostFragment.currentFragment
        get() = childFragmentManager.primaryNavigationFragment

    val NavController.canNavigateUp
        get() = graph.startDestination != currentDestination?.id

    fun NavController.setNavGraphIfAbsent(@NavigationRes graphResId: Int) {
        if (currentDestination == null) {
            setGraph(graphResId)
        }
    }

    fun NavController.setGraphWithStartDestination(
        @NavigationRes graphResId: Int,
        @IdRes startDestId: Int
    ) {
        graph = navInflater.inflate(graphResId).apply {
            startDestination = startDestId
        }
    }

    /**
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun FragmentManager.getNavHostFragment(@IdRes navHostFragmentId: Int) =
        findFragmentById(navHostFragmentId) as NavHostFragment
}
