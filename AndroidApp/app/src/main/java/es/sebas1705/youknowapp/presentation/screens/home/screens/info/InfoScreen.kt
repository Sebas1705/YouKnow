package es.sebas1705.youknowapp.presentation.screens.home.screens.info

import androidx.compose.runtime.Composable
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun InfoScreen(
) {
    InfoDesign()
}

@Composable
private fun InfoDesign(
) {
    ApplyBack(
        R.drawable.back_empty
    ) {

    }

}

@Previews
@Composable
private fun InfoPreview() {
    YouKnowTheme {
        InfoDesign()
    }
}