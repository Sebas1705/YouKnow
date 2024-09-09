package es.sebas1705.youknowapp.presentation.common.customs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import es.sebas1705.youknowapp.ui.theme.TriviaTheme
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.model.Page
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.ui.theme.MediumPadding

@Composable
fun BoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(
                    Brush.horizontalGradient(listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    ))
                )
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        LazyColumn(
            Modifier.padding(horizontal = MediumPadding)
        ) {
            item {
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Previews
@Composable
private fun BoardingPagePreview() {
    TriviaTheme {
        BoardingPage(
            page = Page(
                stringResource(id = R.string.titlePage1),
                stringResource(id = R.string.desPage1),
                R.drawable.back_boarding
            )
        )
    }
}