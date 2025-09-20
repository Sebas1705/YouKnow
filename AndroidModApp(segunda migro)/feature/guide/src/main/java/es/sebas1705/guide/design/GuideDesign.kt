package es.sebas1705.guide.design


import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.guide.composable.GuideBottomBar
import es.sebas1705.designsystem.divider.IVerDivider
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.guide.viewmodel.GuideState
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.Paddings.LargePadding
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.domain.model.ui.PageModel
import es.sebas1705.feature.guide.R

/**
 * Design of the Guide Screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param guideState [GuideState]: State of the guide.
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun GuideDesign(
    windowState: WindowState = WindowState.default(),
    guideState: GuideState = GuideState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onSuccessNavigation: () -> Unit = {},
) {
    //Local:
    val ctx = LocalContext.current

    //String readers:
    val pageList = ctx.generateGuidePages()
    val next = stringResource(id = R.string.feature_guide_next)
    val back = stringResource(id = R.string.feature_guide_back)
    val start = stringResource(id = R.string.feature_guide_start)

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

    //Body:
    if (guideState.isLoading)
        LoadingDialog(windowState)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            GuideBottomBar(pageList.size, pagerState, buttonState, soundPool) {
                onSuccessNavigation()
            }
        }
    ) {
        ApplyBack(
            backId = windowState.backEmpty,
            paddingValues = it
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MediumPadding),
                state = pagerState
            ) { pageIndex ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val paddings = windowState
                        .widthFilter(MediumPadding, LargePadding, HugePadding)
                    TitleSurface(
                        pageList[pageIndex].title,
                        modifier = Modifier.padding(
                            horizontal = paddings
                        )
                    )
                    Spacer(modifier = Modifier.height(MediumPadding))
                    LazyColumn(
                        Modifier.padding(horizontal = paddings)
                    ) {
                        item {
                            IText(
                                text = pageList[pageIndex].introduction,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Justify
                            )
                        }

                        pageList[pageIndex].imagesAndDescription.forEachIndexed { _, page ->

                            item {
                                IVerDivider(
                                    modifier = Modifier.padding(horizontal = paddings),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }

                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = paddings)
                                ) {
                                    Image(
                                        painter = painterResource(page.first),
                                        contentDescription = null,
                                    )
                                }
                            }

                            item {
                                IVerSpacer(
                                    height =
                                    windowState.heightFilter(
                                        SmallestPadding,
                                        SmallPadding,
                                        MediumPadding
                                    )
                                )
                            }

                            item {
                                IText(
                                    text = page.second,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.Justify
                                )
                            }

                            item {
                                IVerDivider(
                                    modifier = Modifier.padding(horizontal = paddings),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

/**
 * Generate a list of [PageModel] to use in the guide
 *
 * @receiver [Context]: context of the app
 *
 * @return [List]<[PageModel]>: list of pages
 *
 * @see PageModel
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
fun Context.generateGuidePages() = listOf(
    PageModel(
        this.getString(R.string.feature_guide_titlePage1),
        this.getString(R.string.feature_guide_introPage1),
        listOf(
            es.sebas1705.core.resources.R.drawable.icon to getString(R.string.feature_guide_des1Page1),
            R.drawable.urjc to getString(R.string.feature_guide_des2Page1),
        )
    ),
    PageModel(
        this.getString(R.string.feature_guide_titlePage2),
        this.getString(R.string.feature_guide_introPage2),
        listOf(
            R.drawable.logos_tools to getString(R.string.feature_guide_des1Page2),
            R.drawable.games to getString(R.string.feature_guide_des2Page2),
        )
    ),
    PageModel(
        this.getString(R.string.feature_guide_titlePage3),
        this.getString(R.string.feature_guide_introPage3),
        listOf(
            es.sebas1705.core.resources.R.drawable.icon to getString(R.string.feature_guide_desPage3)
        )
    )
)

@UiModePreviews
@Composable
private fun GuideDesignPreview() {
    AppTheme {
        GuideDesign()
    }
}