package es.sebas1705.youknow.presentation.features.home.features.main.design
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledTonalButton
import es.sebas1705.youknow.core.composables.buttons.fab.IFAB
import es.sebas1705.youknow.core.composables.cards.IOutlinedCard
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.dialogs.ReloadDialog
import es.sebas1705.youknow.core.composables.divider.IHorDivider
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.features.main.viewmodel.MainState
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import java.util.Locale

/**
 * Design of the Main Screen.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the Home Screen.
 * @param mainState [MainState]: The state of the Main Screen.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSettingsNav () -> Unit: The navigation to the settings.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MainDesign(
    windowState: WindowState = WindowState.default(),
    homeState: HomeState = HomeState.default(),
    mainState: MainState = MainState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onReloadButton: () -> Unit = {},
    onSettingsNav: () -> Unit = {}
) {
    //Local:
    val language = Locale.getDefault().language

    //State:
    var reloadDialog by remember { mutableStateOf(false) }

    //Body:
    ApplyBack(
        windowState.backEmpty
    ) {
        if (mainState.isLoading)
            LoadingDialog(windowState)
        else if (reloadDialog)
            ReloadDialog(
                windowState,
                soundPool,
                onConfirm = {
                    reloadDialog = false
                    onReloadButton()
                },
                onDismiss = { reloadDialog = false }
            )

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title(
                    stringResource(R.string.New_Message),
                    modifier = Modifier.padding(vertical = MediumPadding),
                    style = windowState.widthFilter(
                        MaterialTheme.typography.displaySmall,
                        MaterialTheme.typography.displayMedium,
                        MaterialTheme.typography.displayLarge
                    )
                )
            }

            item {
                val news = mainState.news.associate {
                    if (language == "es") it.titleEs to it.bodyEs
                    else it.titleEn to it.bodyEn
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    state = rememberPagerState(initialPage = 0) { mainState.news.size }
                ) {
                    val new = news.toList()[it]
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IOutlinedCard(
                            modifier = Modifier.fillMaxWidth(
                                windowState.widthFilter(0.9f, 0.7f, 0.5f)
                            ),
                        ) {
                            Spacer(Modifier.height(LargePadding))
                            Title(
                                new.first,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(MediumPadding))
                            IText(
                                new.second,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp)
                            )
                            Spacer(Modifier.height(LargePadding))
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                ) {
                    Spacer(Modifier.height(LargePadding))
                    IHorDivider()
                    Spacer(Modifier.height(SmallPadding))
                    IHorDivider()
                    Spacer(Modifier.height(LargePadding))
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IOutlinedCard(
                        modifier = Modifier.fillMaxWidth(
                            windowState.widthFilter(0.9f, 0.7f, 0.5f)
                        ),
                    ) {
                        Spacer(Modifier.height(MediumPadding))
                        Title(
                            stringResource(R.string.info),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(SmallPadding))
                        Spacer(Modifier.height(SmallPadding))
                        IFilledTonalButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = HugePadding),
                            label = stringResource(R.string.reload),
                            onClick = { reloadDialog = true },
                            enabled = mainState.isLoading.not()
                        )
                        Spacer(Modifier.height(MediumPadding))
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                ) {
                    Spacer(Modifier.height(LargePadding))
                    IHorDivider()
                    Spacer(Modifier.height(SmallPadding))
                    IHorDivider()
                    Spacer(Modifier.height(LargePadding))
                }
            }

            item {
                IResumeCard(
                    stringResource(R.string.Ranking),
                    mainState.ranking.mapIndexed { index, user ->
                        val first =
                            ("${index + 1}º. ${user.first}" + if (user.first == homeState.userModel?.nickName) " (${
                                stringResource(R.string.You)
                            })" else "")
                        val second = "${user.second} ${stringResource(R.string.points)}"
                        first to second
                    }.toMap(),
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                )
                Spacer(Modifier.height(LargePadding))
            }
        }

        IFAB(
            onClick = onSettingsNav,
            contentDescription = stringResource(R.string.settings_title),
            imageVector = Icons.Default.Settings,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding),
            soundPool = soundPool
        )
    }

}

@UiModePreviews
@Composable
private fun MainPreview() {
    YouKnowTheme {
        MainDesign()
    }
}