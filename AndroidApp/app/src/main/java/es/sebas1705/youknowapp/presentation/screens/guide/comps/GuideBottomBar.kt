package es.sebas1705.youknowapp.presentation.screens.guide.comps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomTextButton
import es.sebas1705.youknowapp.ui.theme.IndicatorSize
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.PageIndicatorSeparator
import es.sebas1705.youknowapp.ui.theme.SmallPadding
import es.sebas1705.youknowapp.ui.theme.SmallestPadding
import kotlinx.coroutines.launch

@Composable
fun GuideBottomBar(
    size: Int,
    pagerState: PagerState,
    buttonState: State<List<String>>,
    onSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)

    ) {
        HorizontalDivider(
            modifier = Modifier
                .padding(
                    top = SmallestPadding,
                    bottom = SmallestPadding /2,
                    start = SmallPadding,
                    end = SmallPadding
                )
                .clip(MaterialTheme.shapes.small),
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SmallestPadding)
                .padding(horizontal = MediumPadding)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(PageIndicatorSeparator)
            ) {
                repeat(size) {
                    Box(
                        modifier = Modifier
                            .size(IndicatorSize)
                            .clip(CircleShape)
                            .background(
                                color = if (it == pagerState.currentPage) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface
                            )
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    CustomTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                CustomFilledButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == size - 1) onSuccess()
                            else pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                )
            }
        }
    }
}