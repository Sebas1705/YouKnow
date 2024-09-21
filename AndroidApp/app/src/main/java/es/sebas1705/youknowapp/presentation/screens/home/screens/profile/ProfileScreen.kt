package es.sebas1705.youknowapp.presentation.screens.home.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.analytics.FirebaseAnalytics
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.screens.settings.HomeViewModel
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun ProfileScreen(
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    ProfileDesign(homeViewModel)
}


@Composable
private fun ProfileDesign(
    homeViewModel: HomeViewModel? = null
) {
    val user = homeViewModel?.getUserLogged()

    ApplyBack(
        R.drawable.back_empty
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user?.photoUrl ?: "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
                    .size(200.dp)
            )
        }
    }

}

@Previews
@Composable
private fun ProfilePreview() {
    YouKnowTheme {
        ProfileDesign()
    }
}