package com.github.tatsuyafujisaki.androidplayground

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.hamcrest.Matchers.endsWith

object UITestUtil {
    private fun inputEditText(s: String) {
        onView(
            allOf(withClassName(endsWith("EditText")), withText(Matchers.`is`("")))
        ).perform(ViewActions.replaceText(s))
    }

    private fun clickAlertDialogPositiveButton(@IdRes id: Int) {
        onView(withText(getString(id)))
            .inRoot(isDialog())
            .perform(click())
    }

    private fun <T : RecyclerView.ViewHolder> clickRecyclerViewItem(
        @IdRes recyclerViewId: Int,
        itemText: String
    ) {
        onView(withId(recyclerViewId))
            .perform(
                RecyclerViewActions.actionOnItem<T>(
                    hasDescendant(withText(itemText)), click()
                )
            )
    }

    private fun getString(@IdRes id: Int) =
        InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(id)
}