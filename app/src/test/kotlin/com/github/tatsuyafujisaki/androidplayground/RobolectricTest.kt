package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlin.test.Test
import kotlin.test.assertNotNull
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun test() {
        assertNotNull(context)
    }
}
