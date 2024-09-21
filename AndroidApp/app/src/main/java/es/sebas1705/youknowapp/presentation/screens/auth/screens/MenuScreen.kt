package es.sebas1705.youknowapp.presentation.screens.auth.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.common.buttons.CustomEmptyIconButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomTextButton
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.common.customs.DoubleSpacer
import es.sebas1705.youknowapp.presentation.common.customs.SimpleSpacer
import es.sebas1705.youknowapp.presentation.screens.auth.viewmodel.AuthViewModel
import es.sebas1705.youknowapp.ui.theme.LargePadding
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme


@Composable
fun MenuScreen(
    onSignNavigation: () -> Unit = {},
    onHomeNavigation: () -> Unit = {},
    onLogNavigation: () -> Unit = {},
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    MenuDesign(
        authViewModel = authViewModel,
        onSignNavigation = onSignNavigation,
        onHomeNavigation = onHomeNavigation,
        onLogNavigation = onLogNavigation
    )
}

@Composable
private fun MenuDesign(
    authViewModel: AuthViewModel? = null,
    onSignNavigation: () -> Unit = {},
    onHomeNavigation: () -> Unit = {},
    onLogNavigation: () -> Unit = {},
) {
    val context = LocalContext.current
    ApplyBack(
        R.drawable.back_fill
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LargePadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.3f))
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = stringResource(id = R.string.icon_content),
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Text(
                text = stringResource(id = R.string.textInitial1),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(id = R.string.textInitial2),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(0.4f))
            CustomFilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = stringResource(id = R.string.ToSignUp),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                onClick = onSignNavigation
            )
            SimpleSpacer()
            CustomEmptyIconButton(
                Modifier.clickable { },
                text = stringResource(id = R.string.ToGoogleLog),
                icon = painterResource(id = R.drawable.google)
            ) {
                authViewModel?.authWithGoogle(context,
                    onSuccess = {
                        onHomeNavigation()
                    },
                    onError = {
                        Log.e("Google sign", "Failure for $it")
                    })
            }
            SimpleSpacer()
            /*CustomEmptyIconButton(
                Modifier.clickable { },
                text = stringResource(id = R.string.ToFacebookLog),
                icon = painterResource(id = R.drawable.facebook)
            ) {
                //Login with facebook
            }*/
            DoubleSpacer()
            CustomTextButton(
                modifier = Modifier,
                text = stringResource(id = R.string.ToEmailLog),
                textStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                onClick = onLogNavigation
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Previews
@Composable
private fun MenuPreview() {
    YouKnowTheme {
        MenuDesign()
    }
}