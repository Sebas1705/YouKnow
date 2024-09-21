package es.sebas1705.youknowapp.presentation.screens.guide

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.common.generateGuidePages
import es.sebas1705.youknowapp.presentation.screens.guide.comps.GuideBottomBar
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.viewmodel.GuideViewModel
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun GuideScreen(
    onSuccessNavigation: () -> Unit = {},
) {
    val guideViewModel: GuideViewModel = hiltViewModel()
    GuideDesign(
        onSuccessNavigation = onSuccessNavigation,
        guideViewModel = guideViewModel
    )
}

@Composable
private fun GuideDesign(
    guideViewModel: GuideViewModel? = null,
    onSuccessNavigation: () -> Unit = {},
) {

    //String readers:
    val context = LocalContext.current
    val pageList = context.generateGuidePages()
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            GuideBottomBar(pageList.size, pagerState, buttonState) {
                guideViewModel?.saveFirstTime()
                onSuccessNavigation()
            }
        }
    ) {
        ApplyBack(
            backId = R.drawable.back_empty,
            paddingValues = it
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MediumPadding),
                state = pagerState
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = pageList[it].title,
                        modifier = Modifier.padding(horizontal = MediumPadding),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(MediumPadding))
                    LazyColumn(
                        Modifier.padding(horizontal = MediumPadding)
                    ) {
                        item {
                            Text(
                                text = pageList[it].description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}

@Previews
@Composable
private fun GuideScreenPreview() {
    YouKnowTheme {
        GuideDesign()
    }
}




