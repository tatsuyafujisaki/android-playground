import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(Modifier.width(width))
}

@Preview(showBackground = true)
@Composable
private fun HorizontalSpacerPreview() {
    Column {
        Text("Hello")
        HorizontalSpacer(8.dp)
        Text("World")
    }
}
