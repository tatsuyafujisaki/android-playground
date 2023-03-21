package com.github.tatsuyafujisaki.androidplayground

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.tatsuyafujisaki.androidplayground.debug", context.packageName)
    }
}
