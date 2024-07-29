package es.sebas1705.youknowapp.presentation.common.boardingPage

import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknowapp.R

data class Page (
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)