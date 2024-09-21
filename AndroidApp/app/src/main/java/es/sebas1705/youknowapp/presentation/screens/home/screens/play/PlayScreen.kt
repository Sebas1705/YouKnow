package es.sebas1705.youknowapp.presentation.screens.home.screens.play

import androidx.compose.runtime.Composable
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun PlayScreen(
) {
    PlayDesign()
}

@Composable
private fun PlayDesign(
) {
    ApplyBack(
        R.drawable.back_empty
    ) {

    }

}

@Previews
@Composable
private fun PlayPreview() {
    YouKnowTheme {
        PlayDesign()
    }
}