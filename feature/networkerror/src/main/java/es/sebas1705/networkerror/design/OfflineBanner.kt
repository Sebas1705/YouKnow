package es.sebas1705.networkerror.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.feature.networkerror.R

/**
 * Thin strip shown on top of the app while there is no connection, so what works offline
 * (saved questions and the games built on them) stays usable instead of being replaced by a
 * full-screen error.
 *
 * @param modifier [Modifier]: modifier of the banner
 *
 * @author Sebas1705 07/10/2026
 * @since 1.4.1
 */
@Composable
fun OfflineBanner(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CloudOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onErrorContainer
        )
        Text(
            text = stringResource(id = R.string.feature_networkerror_offline_banner),
            color = MaterialTheme.colorScheme.onErrorContainer,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
