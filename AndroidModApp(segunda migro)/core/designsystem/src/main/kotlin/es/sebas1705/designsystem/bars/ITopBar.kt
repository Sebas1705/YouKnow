package es.sebas1705.designsystem.bars


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.AppTheme

/**
 * Personalized top bar
 *
 * @param title [Composable]: Title
 * @param modifier [Modifier]: Modifier
 * @param navigationIcon [Composable]: Navigation icon
 * @param actions [RowScope.() -> Unit]: Actions
 * @param scrollBehavior [TopAppBarScrollBehavior]: Scroll behavior
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ITopBar(
    title: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit) = { },
    actions: @Composable (RowScope.() -> Unit) = { },
    scrollBehavior: TopAppBarScrollBehavior? = null
) = TopAppBar(
    title,
    modifier,
    navigationIcon,
    actions,
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.surface,
        scrolledContainerColor = MaterialTheme.colorScheme.surface,
        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    scrollBehavior = scrollBehavior,
)

@OptIn(ExperimentalMaterial3Api::class)
@ComposablePreview
@Composable
private fun Preview() = AppTheme {
    ITopBar(
        title = { IText("Hola mundo") }
    )
}
