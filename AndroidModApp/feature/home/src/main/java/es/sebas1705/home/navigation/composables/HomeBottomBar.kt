package es.sebas1705.home.navigation.composables
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.NAV_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.home.navigation.HomeScreens.Companion.HomeItem
import es.sebas1705.home.navigation.HomeScreens.Companion.homes
import es.sebas1705.ui.theme.TonalElevation
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Bottom Navigation Bar for the Home Screen.
 *
 * @param items [List]<[HomeItem]>: that will be displayed in the Navigation Bar.
 * @param selectedItem [Int]: that represents the selected item in the Navigation Bar.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onItemClick [(Int) -> Unit]: that will be called when an item is clicked.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun HomeBottomBar(
    items: List<HomeItem>,
    selectedItem: Int,
    soundPool: Pair<SoundPool, Float>? = null,
    onItemClick: (Int) -> Unit
) = NavigationBar(
    modifier = Modifier
        .fillMaxWidth(),
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = TonalElevation.Level0
) {
    items.forEachIndexed { index, item ->
        //Local:
        val context = LocalContext.current
        val soundId = remember {
            soundPool?.first?.load(context, NAV_BUTTON_SOUND, PRIORITY_SOUND)
        }
        NavigationBarItem(
            modifier = Modifier
                .padding(all = 5.dp),
            selected = index == selectedItem,
            label = {
                Text(
                    text = stringResource(item.strRes),
                    style = MaterialTheme.typography.labelLarge,
                )
            },
            icon = {
                Icon(
                    if (index == selectedItem) item.iconSelected else item.iconUnselected,
                    contentDescription = stringResource(item.strRes),
                    modifier = Modifier
                )
            },
            onClick = {
                if (index != selectedItem) {
                    soundPool?.first?.play(
                        soundId ?: 0,
                        soundPool.second,
                        soundPool.second,
                        PRIORITY_SOUND,
                        LOOP_N,
                        RATE
                    )
                    onItemClick(index)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onTertiary,
                selectedTextColor = MaterialTheme.colorScheme.tertiary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = MaterialTheme.colorScheme.tertiary,
            )
        )
    }
}

@UiModePreviews
@Composable
fun HomeNavigationBarPreview() {
    YouKnowTheme {
        HomeBottomBar(items = homes, selectedItem = 2, onItemClick = { })
    }
}