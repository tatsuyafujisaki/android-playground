package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tatsuyafujisaki.androidplayground.ui.activity.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyActivityTest {
    private lateinit var context: Context
    private lateinit var application: Application

    // "activityScenarioRule" comes from androidTestImplementation 'androidx.test.ext:junit-ktx:*'
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        application = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testActivity() {
        // Another way of getting an ActivityScenario
        // launchActivity comes from androidTestImplementation 'androidx.test:core-ktx:*'
        // val activityScenario = launchActivity<MainActivity>()

        val scenario = activityScenarioRule.scenario

        scenario.onActivity {
            // How to start another Activity
            // it.startActivity(Intent(it, AnotherActivity::class.java))
        }
    }
}
