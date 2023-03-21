import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(
    width: Dp,
    color: Color
) {
    // Divider's "thickness" cannot be used for a vertical divider
    // because "thickness" is always interpreted as the height.
    // Moreover, the width of Divider is infinite unless specified.
    Divider(
        modifier = Modifier
            .width(width)
            .fillMaxHeight(),
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun VerticalDividerPreview() {
    Row(Modifier.height(IntrinsicSize.Min)) {
        Text("Hello")
        VerticalDivider(
            width = 10.dp,
            color = Color.Yellow
        )
        Text("World")
    }
}
