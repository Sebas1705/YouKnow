package es.sebas1705.designsystem.sheets
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

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.ui.theme.TonalElevation
import es.sebas1705.ui.theme.YouKnowTheme
import kotlinx.coroutines.launch

/**
 * IBottomSheet
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable (ColumnScope.() -> Unit)
) = ModalBottomSheet(
    onDismissRequest,
    modifier,
    sheetState = sheetState,
    sheetMaxWidth = BottomSheetDefaults.SheetMaxWidth,
    shape = BottomSheetDefaults.ExpandedShape,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    tonalElevation = TonalElevation.Level5,
    scrimColor = BottomSheetDefaults.ScrimColor,
    dragHandle = { BottomSheetDefaults.DragHandle() },
    contentWindowInsets = { BottomSheetDefaults.windowInsets },
    properties = ModalBottomSheetDefaults.properties,
    content
)


@OptIn(ExperimentalMaterial3Api::class)
@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            sheetState.show()
        }
    }

    IBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
        }
    ) {
        Text("Bottom Sheet")
    }
}

