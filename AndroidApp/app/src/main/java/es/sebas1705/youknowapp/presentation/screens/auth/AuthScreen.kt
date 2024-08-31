package es.sebas1705.youknowapp.presentation.screens.auth

import android.content.Intent
import android.credentials.CredentialManager
import android.credentials.GetCredentialRequest
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.presentation.common.buttons.CustomEmptyIconButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomTextButton
import es.sebas1705.youknowapp.presentation.common.customs.DoubleSpacer
import es.sebas1705.youknowapp.presentation.common.customs.SimpleSpacer
import es.sebas1705.youknowapp.presentation.navigation.Route
import es.sebas1705.youknowapp.presentation.viewmodel.AuthViewModel
import es.sebas1705.youknowapp.ui.theme.LargePadding
import es.sebas1705.youknowapp.ui.theme.TriviaTheme


@Composable
fun AuthScreen(navController: NavController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    AuthSubScreen(navController, authViewModel)
}

@Previews
@Composable
private fun AuthSubScreen(
    navController: NavController? = null,
    authViewModel: AuthViewModel? = null
) {

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                authViewModel?.signWithGoogleToken(
                    account.idToken!!,
                    onSuccess = {navController?.navigate(Route.TriviaScreen.route)},
                    onError = { Log.e("Google sign", "Failure for $it")}
                )
            } catch (e: Exception) {
                Log.d("Login Google error", "error: $e")
            }
        }

    val googleSignInIntent = fun(): Intent {
        val options = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(context.getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, options)
        return googleSignInClient.signInIntent
    }


    TriviaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
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
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(id = R.string.textInitial2),
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
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
                    )
                ) {
                    navController?.navigate(Route.SignScreen.route)
                }
                SimpleSpacer()
                CustomEmptyIconButton(
                    Modifier.clickable { },
                    text = stringResource(id = R.string.ToGoogleLog),
                    icon = painterResource(id = R.drawable.google)
                ) {
                    launcher.launch(googleSignInIntent())
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
                    textStyle = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                ) {
                    navController?.navigate(Route.LogScreen.route)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}