package com.github.tatsuyafujisaki.androidplayground

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.printToString
import kotlin.test.Test
import org.junit.Rule

class MyComposeTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun myComposeTest() {
        val text = "Hello"

        rule.setContent {
            MaterialTheme {
                Text(text = text)
            }
        }

        val mergedTree = rule.onRoot().printToString()
        val unmergedTree = rule.onRoot(useUnmergedTree = true).printToString()

        rule.onRoot().printToLog("MY_MERGED_TREE")
        rule.onRoot(useUnmergedTree = true).printToLog("MY_UNMERGED_TREE")

        rule.onNodeWithText(text = text).assertIsDisplayed()
    }
}
