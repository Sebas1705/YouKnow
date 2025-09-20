package es.sebas1705.home.navigation.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.toReducedString
import es.sebas1705.designsystem.bars.ITopBar
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.feature.home.R

/**
 * TopBar for the Home Screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param points [Int]: Points of the user.
 * @param credits [Int]: Credits of the user.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    windowState: WindowState = WindowState.default(),
    points: Int = 0,
    credits: Int = 0,
) {
    Column {
        val textStyle = windowState.heightFilter(
            MaterialTheme.typography.titleSmall,
            MaterialTheme.typography.titleLarge,
            MaterialTheme.typography.headlineMedium
        )
        val iconSize = windowState.sizeFilter(
            24.dp,
            32.dp,
            48.dp
        )
        ITopBar(
            modifier = Modifier.padding(horizontal = SmallPadding),
            navigationIcon = {
                Row(
                    modifier = Modifier.padding(vertical = SmallestPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IText(
                        text = points.toReducedString(),
                        style = textStyle,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        modifier = Modifier.padding(end = SmallestPadding)
                    )
                    Image(
                        painter = painterResource(R.drawable.point),
                        contentDescription = stringResource(es.sebas1705.core.resources.R.string.core_resources_points),
                        modifier = Modifier
                            .size(iconSize)
                            .padding(end = SmallestPadding)
                    )
                }
            },
            title = {
                Title(
                    text = stringResource(es.sebas1705.core.resources.R.string.core_resources_app_name),
                    style = textStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            actions = {
                IText(
                    text = credits.toReducedString(),
                    style = textStyle,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    modifier = Modifier.padding(end = SmallestPadding)
                )
                Image(
                    painter = painterResource(R.drawable.credit),
                    contentDescription = stringResource(es.sebas1705.core.resources.R.string.core_resources_credits),
                    modifier = Modifier.size(iconSize)
                )
            }
        )
        HorizontalDivider(
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@UiModePreviews
@Composable
fun HomeTopBarPreview() {
    AppTheme {
        HomeTopBar(
            points = 100,
            credits = 100
        )
    }
}