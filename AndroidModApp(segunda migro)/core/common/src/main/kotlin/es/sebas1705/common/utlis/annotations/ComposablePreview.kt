package es.sebas1705.common.utlis.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Annotation to create a preview for composable functions
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(name = "Day")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night")
annotation class ComposablePreview
