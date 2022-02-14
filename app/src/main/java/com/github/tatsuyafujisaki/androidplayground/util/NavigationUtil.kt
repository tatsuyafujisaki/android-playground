package com.github.tatsuyafujisaki.androidplayground.util

import android.os.Bundle
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
        get() = graph.startDestinationId != currentDestination?.id

    /**
     * For debugging purposes, you can ignore the lint error and list non-NavGraph destinations on the back stack.
     * Usage: Timber.d(findNavController().breadcrumb)
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
        true
    }.getOrDefault(false)
}
