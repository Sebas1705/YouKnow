package es.sebas1705.youknowapp.presentation.common.customs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknowapp.ui.theme.LargePadding
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.SmallPadding

@Composable
fun SimpleSpacer() = Spacer(modifier = Modifier.height(SmallPadding))

@Composable
fun RegularSpacer() = Spacer(modifier = Modifier.height(MediumPadding))

@Composable
fun DoubleSpacer() = Spacer(modifier = Modifier.height(LargePadding))