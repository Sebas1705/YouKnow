package es.sebas1705.youknowapp.presentation.common.bottomBars

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.buttons.CustomTextButton
import es.sebas1705.youknowapp.presentation.common.customs.PageIndicator
import es.sebas1705.youknowapp.presentation.viewmodel.OnBoardingEvent
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.OutlineHeight
import es.sebas1705.youknowapp.ui.theme.OutlinePadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageBottomBar(
    size: Int,
    pagerState: PagerState,
    buttonState: State<List<String>>,
    event: (OnBoardingEvent) -> Unit,
    nav: ()->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth(0.1f)
    ){
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(OutlinePadding),
            thickness = OutlineHeight,
            color = MaterialTheme.colorScheme.outline
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MediumPadding)
                .padding(horizontal = MediumPadding)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            PageIndicator(
                pageSize = size,
                selectedPage = pagerState.currentPage
            )

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
                            if (pagerState.currentPage == size - 1) {
                                event(OnBoardingEvent.SaveAppEntry)
                                nav()
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun PreviewPageBottomBar(){

    val size = 3
    //States:
    val pagerState = rememberPagerState(initialPage = 1) {
        size
    }

    val buttonState = remember {
        derivedStateOf {
            listOf(
                if (pagerState.currentPage == 0) "" else "Back",
                if (pagerState.currentPage < size-1) "Next" else "Start"
            )
        }
    }
    PageBottomBar(size,pagerState,buttonState,{},{})
}