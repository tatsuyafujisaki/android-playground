package com.github.tatsuyafujisaki.androidplayground

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class RobolectricTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun test() {
        assertThat(true).isTrue()
    }
}
