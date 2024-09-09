package es.sebas1705.youknowapp.presentation.screens.prelog

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import es.sebas1705.youknowapp.presentation.common.customs.BoardingPage
import es.sebas1705.youknowapp.domain.model.Page
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.navigation.AppRoutes
import es.sebas1705.youknowapp.presentation.viewmodel.PrelogViewModel
import es.sebas1705.youknowapp.ui.theme.TriviaTheme

@Composable
fun GuideScreen(
    navController: NavHostController
) {
    val prelogViewModel: PrelogViewModel = hiltViewModel()
    GuideSubScreen(
        navController = navController,
        prelogViewModel = prelogViewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Previews
@Composable
private fun GuideSubScreen(
    navController: NavHostController? = null,
    prelogViewModel: PrelogViewModel? = null
) {

    //String readers:
    val pageList = generatePages(LocalContext.current)
    val next = stringResource(id = R.string.next)
    val back = stringResource(id = R.string.back)
    val start = stringResource(id = R.string.start)

    //States:
    val pagerState = rememberPagerState(initialPage = 0) { pageList.size }
    val buttonState = remember {
        derivedStateOf {
            listOf(
                if (pagerState.currentPage == 0) "" else back,
                if (pagerState.currentPage < pageList.size - 1) next else start
            )
        }
    }

    TriviaTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                GuideBottomBar(pageList.size, pagerState, buttonState) {
                    prelogViewModel?.saveAppEntry()
                    navController?.navigate(AppRoutes.LogScreen.route) {
                        popUpTo(AppRoutes.AuthScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState
            ) {
                BoardingPage(
                    modifier = Modifier,
                    page = pageList[it]
                )
            }
        }
    }
}


private fun generatePages(context: Context) = listOf(
    Page(
        context.getString(R.string.titlePage1),
        context.getString(R.string.desPage1),
        R.drawable.back_boarding
    ),
    Page(
        context.getString(R.string.titlePage2),
        context.getString(R.string.desPage2),
        R.drawable.back_boarding
    ),
    Page(
        context.getString(R.string.titlePage3),
        context.getString(R.string.desPage3),
        R.drawable.back_boarding
    )
)

