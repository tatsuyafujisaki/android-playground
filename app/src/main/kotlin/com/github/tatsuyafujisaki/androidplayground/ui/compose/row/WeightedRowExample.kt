package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.github.tatsuyafujisaki.androidplayground.R

@Preview(showBackground = true)
@Composable
fun WeightedRowExample(
    @PreviewParameter(LoremIpsum::class) text: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_home_24),
            contentDescription = null,
        )
        Text(
            text = text,
            modifier =
            Modifier
                .align(CenterVertically)
                .weight(1f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Icon(
            painter = painterResource(R.drawable.arrow_forward_24),
            contentDescription = null,
        )
    }
}
