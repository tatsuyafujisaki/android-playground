package com.github.tatsuyafujisaki.androidplayground.util

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment

object NavigationUtil {
    object NavigationController {
        /**
         * Prints a breadcrumb of the navigation stack, not for Navigation Compose.
         */
        @SuppressLint("RestrictedApi")
        fun printBreadcrumb(navController: NavController) {
            Log.d(
                "Breadcrumb",
                navController
                    .currentBackStack
                    .value
                    .map {
                        it.destination
                    }
                    .filterNot {
                        it is NavGraph
                    }
                    .joinToString(" > ") {
                        it.displayName.split('/')[1]
                    },
            )
        }

        /**
         * Prints a breadcrumb of the navigation stack for Navigation Compose.
         */
        @SuppressLint("RestrictedApi")
        fun printComposeBreadcrumb(navController: NavController) {
            Log.d(
                "Breadcrumb",
                navController
                    .currentBackStack
                    .value.joinToString(" > ") {
                        it.destination.route.toString()
                    },
            )
        }

        fun printStartDestinationRoute(navController: NavController) =
            Log.d(
                "StartDestinationRoute",
                navController.graph.findStartDestination().route.toString(),
            )

        fun canNavigateUp(navController: NavController) =
            with(navController) {
                graph.startDestinationId != currentDestination?.id
            }

        fun hasBackStackEntry(
            navController: NavController,
            @IdRes destinationId: Int,
        ) = runCatching {
            navController.getBackStackEntry(destinationId)
        }.isSuccess

        fun setNavGraphIfAbsent(
            navController: NavController,
            @NavigationRes graphResId: Int,
            startDestinationArgs: Bundle?,
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
            @IdRes startDestId: Int,
        ) {
            navController.graph =
                navController.navInflater.inflate(graphResId).apply {
                    setStartDestination(startDestId)
                }
        }
    }

    /**
     * A [FragmentActivity] can contain 0, 1, or more than 1 [NavHostFragment].
     */
    fun getNavHostFragments(activity: FragmentActivity) =
        activity.supportFragmentManager.fragments.filterIsInstance<NavHostFragment>()

    private fun getCurrentFragment(navHostFragment: NavHostFragment) =
        navHostFragment.childFragmentManager.primaryNavigationFragment

    private fun getViewPagerFragment(navHostFragment: NavHostFragment) =
        getCurrentFragment(navHostFragment)

    /**
     * Don't replace ".fragments?.first()" with ".primaryNavigationFragment"
     * because ".primaryNavigationFragment" is null
     * even though ".fragments" contains multiple fragments.
     */
    fun getFragmentInViewPager2(navHostFragment: NavHostFragment) =
        getViewPagerFragment(navHostFragment)?.childFragmentManager?.fragments?.first()
}
