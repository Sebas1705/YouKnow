package es.sebas1705.youknowapp.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import es.sebas1705.youknowapp.presentation.viewmodel.ResponseState
import es.sebas1705.youknowapp.presentation.viewmodel.TriviaViewModel
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.common.Constants.TRIVIA_RESPONSE_EXAMPLE
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.common.decodeUrl
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.screens.auth.viewmodel.AuthViewModel
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun TriviaScreen(
    onSuccessLogOutNavigation: () -> Unit = {},
    onErrorButton: () -> Unit = {},
) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val viewModel: TriviaViewModel = hiltViewModel()
    val responseState by viewModel.responseState.collectAsState()

    when (responseState) {
        is ResponseState.Loading ->
            LoadingDesign()

        is ResponseState.Success ->
            TriviaDesign(
                data = (responseState as ResponseState.Success).triviaResponse,
                authViewModel = authViewModel,
                onSuccessLogOutNavigation = onSuccessLogOutNavigation
            )

        is ResponseState.Error ->
            ErrorDesign(
                message = (responseState as ResponseState.Error).message,
                onErrorButton = onErrorButton
            )

    }
}

@Composable
private fun LoadingDesign() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Previews
@Composable
private fun LoadingPreview() {
    YouKnowTheme {
        LoadingDesign()
    }
}

@Composable
private fun ErrorDesign(
    message: String = "Error",
    onErrorButton: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.error),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = onErrorButton,
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Previews
@Composable
private fun ErrorPreview() {
    YouKnowTheme {
        ErrorDesign()
    }
}

@Composable
private fun TriviaDesign(
    data: TriviaResponse = TRIVIA_RESPONSE_EXAMPLE,
    authViewModel: AuthViewModel? = null,
    onSuccessLogOutNavigation: () -> Unit = {},
) {
    ApplyBack(
        R.drawable.back_fill
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .clickable {
                        authViewModel?.signOut(
                            onSuccess = onSuccessLogOutNavigation,
                            onError = { }
                        )
                    }
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        data.triviaQuestions.forEach {
            item {
                Text(
                    text = it.question.decodeUrl(),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Previews
@Composable
private fun TriviaPreview() {
    YouKnowTheme {
        TriviaDesign()
    }
}





