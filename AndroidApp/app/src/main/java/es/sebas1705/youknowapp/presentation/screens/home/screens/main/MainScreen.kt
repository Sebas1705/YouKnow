package es.sebas1705.youknowapp.presentation.screens.home.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun MainScreen(
) {
    MainDesign()
}

@Composable
private fun MainDesign(
) {
    ApplyBack(
        R.drawable.back_empty
    ) {

    }

}

@Previews
@Composable
private fun MainPreview() {
    YouKnowTheme {
        MainDesign()
    }
}