package es.sebas1705.youknowapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import es.sebas1705.youknowapp.presentation.common.Buttons.NewsButton
import es.sebas1705.youknowapp.presentation.common.Buttons.NewsTextButton
import es.sebas1705.youknowapp.presentation.common.boardingPage.BoardingPage
import es.sebas1705.youknowapp.presentation.common.boardingPage.Page
import es.sebas1705.youknowapp.presentation.common.boardingPage.PageIndicator
import es.sebas1705.youknowapp.presentation.navigation.Route
import es.sebas1705.youknowapp.presentation.viewmodel.OnBoardingEvent
import es.sebas1705.youknowapp.ui.theme.MediumTextHorPadding
import es.sebas1705.youknowapp.ui.theme.PageIndicatorWidth
import kotlinx.coroutines.launch
import es.sebas1705.youknowapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    event: (OnBoardingEvent)->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pageList = listOf(
            Page(
                stringResource(id = R.string.titlePage1),
                stringResource(id = R.string.desPage1),
                R.drawable.back_boarding
            ),
            Page(
                stringResource(id = R.string.titlePage2),
                stringResource(id = R.string.desPage2),
                R.drawable.back_boarding
            ),
            Page(
                stringResource(id = R.string.titlePage3),
                stringResource(id = R.string.desPage3),
                R.drawable.back_boarding
            )
        )

        val pagerState = rememberPagerState(initialPage = 0) {
            pageList.size
        }

        val next = stringResource(id = R.string.next)
        val back = stringResource(id = R.string.back)
        val start = stringResource(id = R.string.start)

        val buttonState = remember {
            derivedStateOf {
                listOf(
                    if (pagerState.currentPage == 0) "" else back,
                    if (pagerState.currentPage < pageList.size - 1) next else start
                )
            }
        }

        HorizontalPager(state = pagerState) {
            BoardingPage(page = pageList[it])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumTextHorPadding)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pageList.size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == pageList.size - 1) {
                                event(OnBoardingEvent.SaveAppEntry)
                                navHostController.navigate(Route.HomeScreen.route)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}