package es.sebas1705.common.utlis.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Annotation to create a preview with the different locales
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(
    showBackground = true,
    name = "Day-en",
    showSystemUi = true,
    locale = "en",
    widthDp = PreviewSettings.WIDTH,
    heightDp = PreviewSettings.HEIGHT
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night-en",
    showSystemUi = true,
    locale = "en",
    widthDp = PreviewSettings.WIDTH,
    heightDp = PreviewSettings.HEIGHT
)
@Preview(
    showBackground = true,
    name = "Day-es",
    showSystemUi = true,
    locale = "es",
    widthDp = PreviewSettings.WIDTH,
    heightDp = PreviewSettings.HEIGHT
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night-es",
    showSystemUi = true,
    locale = "es",
    widthDp = PreviewSettings.WIDTH,
    heightDp = PreviewSettings.HEIGHT
)
annotation class LocalePreviews