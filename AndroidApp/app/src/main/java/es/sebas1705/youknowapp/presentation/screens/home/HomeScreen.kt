package es.sebas1705.youknowapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.ui.theme.TriviaTheme
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.utils.Constants
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.ui.theme.SmallPadding

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeSubScreen(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Previews
@Composable
private fun HomeSubScreen(
    navController: NavController? = null
) {
    TriviaTheme {
        Scaffold(
            bottomBar = { HomeBottomBar(navController) }
        ) {
            ApplyBack(
                R.drawable.back
            ) {

            }
        }
    }
}