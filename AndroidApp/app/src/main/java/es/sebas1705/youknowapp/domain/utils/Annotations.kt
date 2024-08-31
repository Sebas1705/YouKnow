package es.sebas1705.youknowapp.domain.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(showBackground = true, name = "Day")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night")
annotation class Previews