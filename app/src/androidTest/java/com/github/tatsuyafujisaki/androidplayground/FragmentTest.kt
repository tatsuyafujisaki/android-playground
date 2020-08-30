package com.github.tatsuyafujisaki.androidplayground

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle.State
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tatsuyafujisaki.androidplayground.dataClass.SampleRepository
import com.github.tatsuyafujisaki.androidplayground.ui.SampleDialogFragment
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

class FragmentFactoryFactory<F : Fragment>(private val instantiate: () -> F): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return instantiate()
    }
}

@RunWith(AndroidJUnit4::class)
class FragmentTest {
    @Test
    fun testWithGraphicalFragment() {
        val repository = mockk<SampleRepository>()

        val fragmentFactory = FragmentFactoryFactory {
            SampleFragment.newInstance("foo", "bar", repository)
        }

        val scenario = launchFragmentInContainer<SampleFragment>(
            fragmentArgs = bundleOf("Apple" to 100, "Orange" to 200),
            factory = fragmentFactory
        )

        // Demonstration of specifying the state. If omitted, the state moves to RESUMED.
        scenario.moveToState(State.STARTED)

        // Demonstration of interacting with the fragment
        scenario.onFragment {
            // Do something
        }

        // Demonstration of recreating the activity.
        scenario.recreate()

        onView(withId(R.id.sample_text_view))
            .check(matches(withText("Sample")))

        onView(withId(R.id.sample_button))
            .perform(click())
    }

    @Test
    fun testWithNonGraphicalFragment() {
        val scenario = launchFragment<HomeFragment>(
            bundleOf("Apple" to 100, "Orange" to 200)
        )
    }

    @Test
    fun testDismissDialogFragment() {
        // Assumes that "MyDialogFragment" extends the DialogFragment class.
        with(launchFragment<SampleDialogFragment>()) {
            onFragment {
                assertThat(it.dialog).isNotNull()
                assertThat(it.requireDialog().isShowing).isTrue()
                it.dismiss()
                it.parentFragmentManager.executePendingTransactions()
                assertThat(it.dialog).isNull()
            }

            // Assumes that the dialog had a button containing the text "Cancel".
            onView(withText("Cancel")).check(doesNotExist())
        }
    }
}