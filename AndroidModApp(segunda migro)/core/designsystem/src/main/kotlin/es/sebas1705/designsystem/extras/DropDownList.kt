package es.sebas1705.designsystem.extras

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
import es.sebas1705.core.designsystem.R
import es.sebas1705.ui.theme.TonalElevation
import es.sebas1705.designsystem.textfields.IOutlinedTextField

/**
 * Dropdown list
 *
 * @param valueRes [Int]: Value resource
 * @param onValueChange [Function1<String, Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param dropDownMenuConstructor [@Composable ColumnScope.(onChanged: (Int) -> Unit) -> Unit]: Drop down menu constructor
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownList(
    valueRes: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
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
            label = stringResource(R.string.core_designsystem_select_option),
            readOnly = true,
            onValueChange = onValueChange,
            trailingIcon = (if (!expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropUp) to {},
            leadingIcon = null,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
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