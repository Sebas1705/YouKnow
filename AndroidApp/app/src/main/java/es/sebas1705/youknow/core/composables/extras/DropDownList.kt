package es.sebas1705.youknow.core.composables.extras
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation

/**
 * Dropdown list
 *
 * @param valueRes [Int]: Value resource
 * @param onValueChange [Function1<String, Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param dropDownMenuConstructor [@Composable ColumnScope.(onChanged: (Int) -> Unit) -> Unit]: Drop down menu constructor
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownList(
    valueRes: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    dropDownMenuConstructor: @Composable ColumnScope.(onChanged: (Int) -> Unit) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableIntStateOf(valueRes) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        IOutlinedTextField(
            value = stringResource(valueRes),
            label = stringResource(R.string.select_option),
            readOnly = true,
            onValueChange = onValueChange,
            trailingIcon = (if (!expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropUp) to {},
            leadingIcon = null,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            soundPool = soundPool
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.small,
            shadowElevation = TonalElevation.Level5
        ) {
            dropDownMenuConstructor {
                selectedOption = it
                expanded = false
            }
        }
    }
}