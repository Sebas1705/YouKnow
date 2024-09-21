package es.sebas1705.youknowapp.presentation.common.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import java.util.Locale

@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    onClick: ()->Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.medium
    ){
        Text(
            text = text.uppercase(Locale.ROOT),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}