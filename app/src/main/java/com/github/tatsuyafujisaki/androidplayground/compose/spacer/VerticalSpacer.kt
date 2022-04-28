import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(height: Dp) {
    Spacer(Modifier.height(height))
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalSpacer() {
    Row {
        Text("Hello")
        VerticalSpacer(8.dp)
        Text("World")
    }
}
