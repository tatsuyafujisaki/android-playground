package com.github.tatsuyafujisaki.androidplayground.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment

object NavigationUtil {
    /**
     * For debugging purposes, you can ignore the lint error and list non-NavGraph destinations on the back stack.
     * Usage: Log.d(tag, findNavController().breadcrumb)
     */
    val NavController.breadcrumb
        get() = "Breadcrumb: " + backQueue
            .map {
                it.destination
            }
            .filterNot {
                it is NavGraph
            }
            .joinToString(" > ") {
                it.displayName.split('/')[1]
            }

    /**
     * A [FragmentActivity] can contain 0, 1, or more than 1 [NavHostFragment].
     */
    fun getNavHostFragments(activity: FragmentActivity) =
        activity.supportFragmentManager.fragments.filterIsInstance<NavHostFragment>()

    fun getCurrentFragment(navHostFragment: NavHostFragment) =
        navHostFragment.childFragmentManager.primaryNavigationFragment

    private fun getViewPager2Fragment(navHostFragment: NavHostFragment) =
        getCurrentFragment(navHostFragment)

    /**
     * Don't replace ".fragments?.first()" with ".primaryNavigationFragment"
     * because ".primaryNavigationFragment" is null
     * even though ".fragments" contains multiple fragments.
     */
    fun getFragmentInViewPager2(navHostFragment: NavHostFragment) =
        getViewPager2Fragment(navHostFragment)?.childFragmentManager?.fragments?.first()

    fun canNavigateUp(navController: NavController) = with(navController) {
        graph.startDestinationId != currentDestination?.id
    }

    fun setNavGraphIfAbsent(
        navController: NavController,
        @NavigationRes graphResId: Int,
        startDestinationArgs: Bundle?
    ) {
        if (navController.currentDestination != null) return
        if (startDestinationArgs != null) {
            navController.setGraph(graphResId, startDestinationArgs)
        } else {
            navController.setGraph(graphResId)
        }
    }

    fun setGraphWithStartDestination(
        navController: NavController,
        @NavigationRes graphResId: Int,
        @IdRes startDestId: Int
    ) {
        navController.graph = navController.navInflater.inflate(graphResId).apply {
            setStartDestination(startDestId)
        }
    }

    fun setGraphWithStartDestinationAndArgs(
        navController: NavController,
        @NavigationRes graphResId: Int,
        @IdRes startDestId: Int
    ) {
        val graph = navController.navInflater.inflate(graphResId).apply {
            setStartDestination(startDestId)
        }
        // navController.setGraph(graph /*, MyStartDestinationArgs(arg1, arg2).toBundle() */)
    }

    fun hasBackStackEntry(
        navController: NavController,
        @IdRes destinationId: Int
    ) = runCatching {
        navController.getBackStackEntry(destinationId)
    }.isSuccess
}
