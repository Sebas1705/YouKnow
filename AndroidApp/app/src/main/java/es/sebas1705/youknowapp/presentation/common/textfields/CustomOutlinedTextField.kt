package es.sebas1705.youknowapp.presentation.common.textfields


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    label: String = "",
    password: Boolean = false,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit = {}
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (!isFocused) Text(text = placeholder)
        },
        label = {
            Text(text = label)
        },
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
            },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        textStyle = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible || !password) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (password) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Menu else Icons.Filled.MoreVert,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }

        },
        singleLine = singleLine
    )
}

@Composable
fun CustomTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    label: String = "",
    onValueChange: (String) -> Unit = {}
) = CustomOutlinedTextField(
    modifier = modifier,
    value = value,
    placeholder = placeholder,
    label = label,
    password = true,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    onValueChange = onValueChange
)

@Composable
fun CustomTextFieldEmail(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    label: String = "",
    onValueChange: (String) -> Unit = {}
) = CustomOutlinedTextField(
    modifier = modifier,
    value = value,
    placeholder = placeholder,
    label = label,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    onValueChange = onValueChange
)
