package com.github.tatsuyafujisaki.androidplayground

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat("com.github.tatsuyafujisaki.androidplayground.debug")
            .isEqualTo(context.packageName)
    }
}
