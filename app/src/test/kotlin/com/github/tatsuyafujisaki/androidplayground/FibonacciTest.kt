package com.github.tatsuyafujisaki.androidplayground

import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class FibonacciTest(private val expected: Int, private val n: Int) {
    @Test
    fun test() {
        assertEquals(expected, Fibonacci.nth(n))
    }

    companion object {
        @JvmStatic
        @Parameters(name = "{index}: {0}=test({1})")
        fun parameters(): Collection<Array<Any>> {
            return listOf(
                arrayOf(1, 0),
                arrayOf(1, 1),
                arrayOf(2, 2),
                arrayOf(3, 3),
                arrayOf(5, 4),
                arrayOf(8, 5),
            )
        }
    }
}

object Fibonacci {
    fun nth(n: Int): Int =
        when (n) {
            0, 1 -> 1
            else -> nth(n - 1) + nth(n - 2)
        }
}
