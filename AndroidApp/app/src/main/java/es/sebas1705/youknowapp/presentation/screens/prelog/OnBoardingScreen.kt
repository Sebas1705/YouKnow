package es.sebas1705.youknowapp.presentation.screens.prelog

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import es.sebas1705.youknowapp.presentation.common.customs.BoardingPage
import es.sebas1705.youknowapp.domain.model.Page
import es.sebas1705.youknowapp.presentation.viewmodel.OnBoardingEvent
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.presentation.common.bottomBars.PageBottomBar
import es.sebas1705.youknowapp.presentation.navigation.Route

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    event: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //String readers:
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
        val next = stringResource(id = R.string.next)
        val back = stringResource(id = R.string.back)
        val start = stringResource(id = R.string.start)

        //States:
        val pagerState = rememberPagerState(initialPage = 0) {
            pageList.size
        }

        val buttonState = remember {
            derivedStateOf {
                listOf(
                    if (pagerState.currentPage == 0) "" else back,
                    if (pagerState.currentPage < pageList.size - 1) next else start
                )
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                PageBottomBar(pageList.size, pagerState, buttonState, event) {
                    navController.navigate(
                        Route.LogScreen.route
                    )
                }
            }
        ) {
            MyHorizontalPager(pagerState, pageList)
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MyHorizontalPager(
    pagerState: PagerState,
    pageList: List<Page>
) {
    HorizontalPager(
        modifier = Modifier.padding(),
        state = pagerState
    ) {
        BoardingPage(modifier = Modifier.fillMaxHeight(0.8f), page = pageList[it])
    }
}

