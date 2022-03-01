import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
    Divider(
        modifier = Modifier
            .width(width)
            .fillMaxHeight(),
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalDivider() {
    Row(Modifier.height(IntrinsicSize.Min)) {
        Text("Hello")
        VerticalDivider(
            width = 10.dp,
            color = Color.Yellow
        )
        Text("World")
    }
}
