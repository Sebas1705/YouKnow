package es.sebas1705.youknowapp.presentation.common.customs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.utils.Previews
import es.sebas1705.youknowapp.presentation.common.textfields.CustomTextFieldEmail
import es.sebas1705.youknowapp.presentation.common.textfields.CustomTextFieldPassword
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.TriviaTheme

@Composable
fun EmailPassFields(
    emailValue: String,
    passValue: String,
    onEmailChange: (String)->Unit = {},
    onPassChange: (String)->Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .padding(horizontal = MediumPadding),
        verticalArrangement = Arrangement.Bottom
    ) {
        CustomTextFieldEmail(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            value = emailValue,
            placeholder = stringResource(id = R.string.email),
            label = stringResource(id = R.string.email),
            onValueChange = onEmailChange
        )
    }
    RegularSpacer()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
            .padding(horizontal = MediumPadding)
    ) {
        CustomTextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            value = passValue,
            placeholder = stringResource(id = R.string.password),
            label = stringResource(id = R.string.password),
            onValueChange = onPassChange
        )
    }
}