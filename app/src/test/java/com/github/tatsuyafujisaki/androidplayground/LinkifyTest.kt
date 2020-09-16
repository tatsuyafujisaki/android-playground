package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LinkifyTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun test() {
        assertThat(true).isTrue()
    }
}
