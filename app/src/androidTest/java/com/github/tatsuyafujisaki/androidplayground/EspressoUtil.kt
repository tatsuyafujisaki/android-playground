package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.hamcrest.Matchers.endsWith

fun inputEditText(s: String) {
    onView(
        allOf(withClassName(endsWith("EditText")), withText(Matchers.`is`("")))
    ).perform(ViewActions.replaceText(s))
}

fun clickDialogPositiveButton(context: Context, @IdRes id: Int) {
    onView(withText(context.resources.getString(id)))
        .inRoot(isDialog())
        .perform(click())
}

fun <T : RecyclerView.ViewHolder> clickRecyclerViewItem(
    @IdRes recyclerViewId: Int,
    text: String
) {
    onView(withId(recyclerViewId))
        .perform(
            RecyclerViewActions.actionOnItem<T>(
                hasDescendant(withText(text)), click()
            )
        )
}

fun checkIfNotExist(context: Context, @IdRes id: Int) {
    onView(withText(context.resources.getString(id)))
        .check(doesNotExist())
}
