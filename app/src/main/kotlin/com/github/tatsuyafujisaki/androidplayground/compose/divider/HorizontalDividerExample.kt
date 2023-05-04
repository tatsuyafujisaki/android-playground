import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDividerExample(
    color: Color,
    thickness: Dp
) {
    Divider(
        color = color,
        thickness = thickness
    )
}

@Preview(showBackground = true)
@Composable
private fun HorizontalDividerPreview() {
    Column {
        Text("Hello")
        HorizontalDividerExample(
            color = Color.Yellow,
            thickness = 10.dp
        )
        Text("World")
    }
}
