package com.github.tatsuyafujisaki.androidplayground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import kotlin.test.Test

class MyComposeTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun myComposeTest() {
        val text = "Hello"

        rule.setContent {
            MaterialTheme {
                Text(text =text)
            }
        }

        rule.onRoot().printToLog("MY_MERGED_TREE")
        rule.onRoot(useUnmergedTree = true).printToLog("MY_UNMERGED_TREE")

        rule.onNodeWithText(text = text).assertIsDisplayed()
    }
}
