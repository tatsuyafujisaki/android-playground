package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

object FragmentUtil {
    private val FragmentManager.fragmentNames
        get() = fragments.map { it.javaClass.simpleName }

    val FragmentManager.navHostFragments
        get() = fragments.filterIsInstance<NavHostFragment>()

    val NavHostFragment.currentFragment
        get() = childFragmentManager.primaryNavigationFragment

    private val FragmentManager.backStackEntryNames
        get() = (0 until backStackEntryCount)
            .map {
                val tag = getBackStackEntryAt(it).name
                /**
                 * [FragmentManager.findFragmentByTag] returns null if the [BackStackEntry] has NOT been added with the tag.
                 */
                findFragmentByTag(tag)?.javaClass?.simpleName ?: tag
            }

    val NavController.canNavigateUp
        get() = graph.startDestination != currentDestination?.id

    fun Fragment.clearViewModels() {
        viewModelStore.clear()
    }

    fun Fragment.logFragmentManagers() {
        fun FragmentManager.log(tagPrefix: String) {
            Log.d("$tagPrefix.fragments", fragmentNames.joinToString(","))
            Log.d("$tagPrefix.backStackEntries", backStackEntryNames.joinToString(","))
        }

        val fragmentName = javaClass.simpleName
        parentFragmentManager.log("$fragmentName.parentFragmentManager")
        childFragmentManager.log("$fragmentName.childFragmentManager")
    }

    /**
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun FragmentManager.getNavHostFragment(@IdRes navHostFragmentId: Int) =
        findFragmentById(navHostFragmentId) as? NavHostFragment

    inline fun <reified F : Fragment> FragmentManager.replaceFragment(
        @IdRes containerViewId: Int,
        fragmentTag: String? = null,
        transactionName: String? = null
    ) {
        commit {
            if (fragmentTag.isNullOrBlank()) {
                replace<F>(containerViewId)
            } else {
                replace<F>(containerViewId, fragmentTag)
            }
            setReorderingAllowed(true)
            addToBackStack(transactionName)
        }
    }

    fun FragmentManager.popBackStack(popFragmentCount: Int) {
        popBackStack(
            getBackStackEntryAt(backStackEntryCount - popFragmentCount).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}
