package es.sebas1705.survey.design


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.buttons.icon.IFilledTonalIconButton
import es.sebas1705.designsystem.cards.IStickerCard
import es.sebas1705.designsystem.dialogs.LoadingDialog
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.textfields.IOutlinedTextField
import es.sebas1705.feature.survey.R
import es.sebas1705.survey.composables.SurveySectionCard
import es.sebas1705.survey.schema.SurveySchema
import es.sebas1705.survey.viewmodel.SurveyState
import es.sebas1705.ui.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Survey Design of the app: a [HorizontalPager] over [SurveySchema.pages], "About you" fields on
 * the first page, and a Previous/Next/Submit bar. Shows a thank-you card once [SurveyState.submitted].
 *
 * @param windowState [WindowState]: state of the window
 * @param soundPool [Pair]<[SoundPool], [Float]>: sound pool and volume
 * @param onBack () -> Unit: action to go back
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun SurveyDesign(
    windowState: WindowState = WindowState.default(),
    surveyState: SurveyState = SurveyState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onBack: () -> Unit = {},
    onUpdateAge: (String) -> Unit = {},
    onUpdateProfession: (String) -> Unit = {},
    onUpdateAndroidKnowing: (Int) -> Unit = {},
    onUpdateApplicationsKnowing: (Int) -> Unit = {},
    onUpdateGamesKnowing: (Int) -> Unit = {},
    onUpdateSocialNetworksKnowing: (Int) -> Unit = {},
    onUpdateOtherKnowing: (String) -> Unit = {},
    onOpinionPoints: (String, Int) -> Unit = { _, _ -> },
    onOpinionText: (String, String) -> Unit = { _, _ -> },
    onSubmit: () -> Unit = {},
) {
    BackHandler { onBack() }
    val pagerState = rememberPagerState(initialPage = 0) { SurveySchema.pages.size }
    val scope = rememberCoroutineScope()

    ApplyBack(backId = windowState.backEmpty) {
        if (surveyState.isLoading) LoadingDialog(windowState)

        if (surveyState.submitted) {
            SurveyThanksContent(windowState, onBack)
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IFilledTonalIconButton(
                        onClick = onBack,
                        contentDescription = stringResource(es.sebas1705.core.resources.R.string.core_resources_back),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    )
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text(
                            text = stringResource(R.string.feature_survey_title),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(
                                R.string.feature_survey_page_of,
                                pagerState.currentPage + 1,
                                SurveySchema.pages.size
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { pageIndex ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        Column(
                            modifier = Modifier
                                .widthIn(max = 560.dp)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            if (pageIndex == 0) AboutYouCard(
                                surveyState = surveyState,
                                onUpdateAge = onUpdateAge,
                                onUpdateProfession = onUpdateProfession,
                                onUpdateAndroidKnowing = onUpdateAndroidKnowing,
                                onUpdateApplicationsKnowing = onUpdateApplicationsKnowing,
                                onUpdateGamesKnowing = onUpdateGamesKnowing,
                                onUpdateSocialNetworksKnowing = onUpdateSocialNetworksKnowing,
                                onUpdateOtherKnowing = onUpdateOtherKnowing,
                            )
                            SurveySchema.pages[pageIndex].forEach { section ->
                                SurveySectionCard(
                                    section = section,
                                    opinions = surveyState.opinions,
                                    onPointsChange = onOpinionPoints,
                                    onTextChange = onOpinionText,
                                )
                            }
                            Spacer(Modifier.height(72.dp))
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IOutlinedButton(
                        label = stringResource(R.string.feature_survey_previous),
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } },
                        enabled = pagerState.currentPage > 0,
                    )
                    if (pagerState.currentPage == SurveySchema.pages.lastIndex) IFilledButton(
                        label = stringResource(R.string.feature_survey_submit),
                        onClick = onSubmit,
                    ) else IFilledButton(
                        label = stringResource(R.string.feature_survey_next),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                    )
                }
            }
        }
    }
}

@Composable
private fun AboutYouCard(
    surveyState: SurveyState,
    onUpdateAge: (String) -> Unit,
    onUpdateProfession: (String) -> Unit,
    onUpdateAndroidKnowing: (Int) -> Unit,
    onUpdateApplicationsKnowing: (Int) -> Unit,
    onUpdateGamesKnowing: (Int) -> Unit,
    onUpdateSocialNetworksKnowing: (Int) -> Unit,
    onUpdateOtherKnowing: (String) -> Unit,
) {
    IStickerCard(
        modifier = Modifier.fillMaxWidth(),
        border = MaterialTheme.colorScheme.tertiary,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.feature_survey_about_you),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = stringResource(R.string.feature_survey_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            IOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                value = surveyState.age,
                onValueChange = { if (it.length <= 3 && it.all(Char::isDigit)) onUpdateAge(it) },
                label = stringResource(R.string.feature_survey_age),
            )
            IOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                value = surveyState.profession,
                onValueChange = onUpdateProfession,
                label = stringResource(R.string.feature_survey_profession),
            )
            KnowingSlider(stringResource(R.string.feature_survey_android_knowing), surveyState.androidKnowing, onUpdateAndroidKnowing)
            KnowingSlider(stringResource(R.string.feature_survey_applications_knowing), surveyState.applicationsKnowing, onUpdateApplicationsKnowing)
            KnowingSlider(stringResource(R.string.feature_survey_games_knowing), surveyState.gamesKnowing, onUpdateGamesKnowing)
            KnowingSlider(stringResource(R.string.feature_survey_social_networks_knowing), surveyState.socialNetworksKnowing, onUpdateSocialNetworksKnowing)
            IOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                value = surveyState.otherKnowing,
                onValueChange = onUpdateOtherKnowing,
                label = stringResource(R.string.feature_survey_other_knowing),
                singleLine = false,
                maxLines = 3,
            )
        }
    }
}

/** A 0..5 self-rating slider for one of the "how much do you know about X" questions. */
@Composable
private fun KnowingSlider(label: String, value: Int, onChange: (Int) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label: $value/5",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Slider(
            value = value.toFloat(),
            onValueChange = { onChange(it.toInt()) },
            valueRange = 0f..5f,
            steps = 4,
        )
    }
}

@Composable
private fun SurveyThanksContent(windowState: WindowState, onClose: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        IStickerCard(
            modifier = Modifier
                .widthIn(max = 420.dp)
                .fillMaxWidth()
                .padding(24.dp),
            border = MaterialTheme.colorScheme.tertiary,
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.feature_survey_thanks_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.feature_survey_thanks_body),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
                )
                IFilledButton(
                    label = stringResource(R.string.feature_survey_close),
                    onClick = onClose,
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun SurveyPreview() {
    AppTheme {
        SurveyDesign()
    }
}
