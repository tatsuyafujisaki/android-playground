package com.github.tatsuyafujisaki.androidplayground.util

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavGraph
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

    fun NavController.setGraphWithStartDestinationAndArgs(
        @NavigationRes graphResId: Int,
        @IdRes startDestId: Int
    ) {
        val graph = navInflater.inflate(graphResId).apply {
            startDestination = startDestId
        }
        setGraph(graph /*, MyReceivingDestinationArgs(arg1, arg2).toBundle() */)
    }

    /**
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun FragmentManager.getNavHostFragment(@IdRes navHostFragmentId: Int) =
        findFragmentById(navHostFragmentId) as NavHostFragment

    /**
     * For debugging purposes, you can ignore the lint error and list non-NavGraph destinations on the back stack.
     */
    @SuppressLint("RestrictedApi")
    fun NavController.printBackStack() {
        backStack
            .map {
                it.destination
            }
            .filterNot {
                it is NavGraph
            }
            .joinToString(" > ") {
                it.displayName.split('/')[1]
            }
    }
}
