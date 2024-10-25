package es.sebas1705.youknow.core.utlis
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

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

object PreviewSettings{
    const val WIDTH = 392
    const val HEIGHT = 850
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(showBackground = true, name = "Day", showSystemUi = true, locale = "en", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night", showSystemUi = true, locale = "es", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
annotation class UiModePreviews

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Preview(showBackground = true, name = "Day-en", showSystemUi = true, locale = "en", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night-en", showSystemUi = true, locale = "en", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
@Preview(showBackground = true, name = "Day-es", showSystemUi = true, locale = "es", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Night-es", showSystemUi = true, locale = "es", widthDp = PreviewSettings.WIDTH, heightDp = PreviewSettings.HEIGHT)
annotation class LocalePreviews

