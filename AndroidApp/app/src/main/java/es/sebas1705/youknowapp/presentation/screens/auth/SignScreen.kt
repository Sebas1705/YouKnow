package es.sebas1705.youknowapp.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.customs.EmailPassFields
import es.sebas1705.youknowapp.presentation.navigation.Route
import es.sebas1705.youknowapp.presentation.viewmodel.AuthViewModel
import es.sebas1705.youknowapp.ui.theme.LargePadding
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.SmallPadding
import es.sebas1705.youknowapp.ui.theme.TriviaTheme

@Composable
fun SignScreen(navController: NavController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    SignSubScreen(navController, authViewModel)
}

@Previews
@Composable
fun SignSubScreen(
    navController: NavController? = null,
    authViewModel: AuthViewModel? = null
) {

    val s = stringResource(id = R.string.textLogError)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf(s) }

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
                    .padding(horizontal = MediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Image(
                    painter = painterResource(id = R.drawable.sign_user),
                    contentDescription = stringResource(id = R.string.icon_content),
                    modifier = Modifier.fillMaxHeight(0.2f).clip(CircleShape)
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.textSignUp),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                EmailPassFields(
                    emailValue = email,
                    passValue = password,
                    onEmailChange = { email = it },
                    onPassChange = { password = it }
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.15f))
                CustomFilledButton(
                    text = stringResource(id = R.string.signup),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = LargePadding)
                        .fillMaxHeight(0.3f)
                ) {
                    authViewModel?.authWithEmail(
                        create = true,
                        email = email,
                        password = password,
                        onSuccess = { navController?.navigate(Route.LogScreen.route) },
                        onError = {
                            if (it != null) errorText = it
                            error = true
                        }
                    )
                }
            }
        }
        if(error){
            AlertDialog(
                onDismissRequest = { error = false },
                title = {
                    Text(text = stringResource(R.string.titleError))
                },
                text = {
                    Text(text = errorText)
                },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { error = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}