import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(
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
private fun HorizontalSpacerPreview() {
    Column {
        Text("Hello")
        HorizontalDivider(
            color = Color.Yellow,
            thickness = 10.dp
        )
        Text("World")
    }
}
