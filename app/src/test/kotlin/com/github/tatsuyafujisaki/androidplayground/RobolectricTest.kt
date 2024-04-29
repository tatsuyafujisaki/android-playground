package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.Test
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun test() {
        assertNotNull(context)
    }
}
