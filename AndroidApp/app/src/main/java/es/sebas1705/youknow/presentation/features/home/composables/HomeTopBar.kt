package es.sebas1705.youknow.presentation.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.toReducedString
import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * TopBar for the Home Screen.
 *
 * @param title [String]: Title of the TopBar.
 * @param onLogoutButton () -> Unit: Action to be executed when the logout button is clicked.
 *
 * @see TopAppBar
 * @see IconButton
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    windowState: WindowState = WindowState.default(),
    userModel: UserModel? = null,
) {
    Column {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            tonalElevation = TonalElevation.Level5
        ) {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = SmallestPadding, end = MediumPadding),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val textStyle = windowState.heightFilter(
                            MaterialTheme.typography.headlineSmall,
                            MaterialTheme.typography.headlineLarge,
                            MaterialTheme.typography.titleMedium
                        )
                        Row(
                            modifier = Modifier.padding(vertical = SmallestPadding),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IText(
                                text = stringResource( R.string.points) + ": ${userModel?.points?.toReducedString()}",
                                style = textStyle,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                modifier = Modifier.padding(end = SmallestPadding)
                            )
                            Image(
                                painter = painterResource(R.drawable.point),
                                contentDescription = stringResource(R.string.points),
                                modifier = Modifier.padding(end = SmallestPadding)
                            )
                        }
                        Row(
                            modifier = Modifier.padding(vertical = SmallestPadding),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            IText(
                                text = stringResource(R.string.credits) + ": ${userModel?.credits?.toReducedString()}",
                                style = textStyle,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                modifier = Modifier.padding(end = SmallestPadding)
                            )
                            Image(
                                painter = painterResource(R.drawable.credit),
                                contentDescription = stringResource(R.string.credits),
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface,
                )
            )
        }
        HorizontalDivider(
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/**
 * Preview of the [HomeTopBar].
 *
 * @see HomeTopBar
 */
@UiModePreviews
@Composable
fun HomeTopBarPreview() {
    YouKnowTheme {
        HomeTopBar(
            userModel = UserModel(
                firebaseId = "1",
                nickName = "Sebastián Ramiro Entrerrios García",
                email = "",
                credits = 0,
                points = 0,
                provider = ProviderAuth.EMAIL,
                photoUrl = "",
                friends = emptyList(),
                groupId = "TODO()",
            )
        )
    }
}