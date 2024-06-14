package com.github.tatsuyafujisaki.androidplayground.ui.compose.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lineBreakOptions = listOf(
    "Simple" to LineBreak.Simple,
    "Paragraph" to LineBreak.Paragraph,
    "Heading" to LineBreak.Heading,
    "Custom" to LineBreak(
        strategy = LineBreak.Strategy.Balanced,
        strictness = LineBreak.Strictness.Strict,
        wordBreak = LineBreak.WordBreak.Default
    )
)

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/LineBreak
 */
private const val demoText = "This is an example text\n今日は自由が丘で焼き鳥を食べます。"
private val presetNameStyle = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)

@Preview
@Composable
fun TextLineBreakingDemo() {
    val selectedFontSize = remember { mutableFloatStateOf(16f) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Font size: ${selectedFontSize.floatValue}")
        Slider(
            value = selectedFontSize.floatValue,
            onValueChange = { value -> selectedFontSize.floatValue = value },
            valueRange = 8f..48f
        )

        Row(Modifier.fillMaxWidth()) {
            val textModifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 5.dp)
                .border(1.dp, Color.Gray)

            lineBreakOptions.forEach { (presetName, preset) ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(presetNameStyle) {
                            append(presetName)
                            append(":\n")
                        }
                        append(demoText)
                    },
                    style = TextStyle(
                        lineBreak = preset,
                        fontSize = selectedFontSize.floatValue.sp
                    ),
                    modifier = textModifier.weight(1f)
                )
            }
        }
    }
}
