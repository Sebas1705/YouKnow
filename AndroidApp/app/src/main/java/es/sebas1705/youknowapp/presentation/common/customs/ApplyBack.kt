package es.sebas1705.youknowapp.presentation.common.customs

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import es.sebas1705.youknowapp.R

@Composable
fun ApplyBack(
    backId: Int,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    applyCondition: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues)
    ) {
        if (applyCondition) Image(
            painter = painterResource(id = backId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}