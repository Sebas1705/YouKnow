package es.sebas1705.designsystem.sheets


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
import es.sebas1705.common.utlis.ComposablePreview
import es.sebas1705.ui.theme.TonalElevation
import es.sebas1705.ui.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * IBottomSheet
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
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
@ComposablePreview
@Composable
private fun Preview() = AppTheme {
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

