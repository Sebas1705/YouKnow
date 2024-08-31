package es.sebas1705.youknowapp.presentation.common.customs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.ui.theme.IndicatorSize
import es.sebas1705.youknowapp.ui.theme.PageIndicatorSeparator
import es.sebas1705.youknowapp.ui.theme.TriviaTheme

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onSurface
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(PageIndicatorSeparator)
    ){
        repeat(pageSize){
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if(it==selectedPage) selectedColor else unSelectedColor)
            )
        }
    }
}

@Previews
@Composable
private fun PageIndicatorPreview(){
    TriviaTheme {
        PageIndicator(
            pageSize = 3,
            selectedPage = 1
        )
    }
}