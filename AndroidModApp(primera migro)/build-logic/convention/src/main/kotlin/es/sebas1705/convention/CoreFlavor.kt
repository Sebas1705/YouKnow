package es.sebas1705.convention
/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

enum class FlavorDimension {
    CONTENT_TYPE
}

/**
 * Enum class that represents the different flavors of the application.
 */
enum class CoreFlavor(val dimension: FlavorDimension, val applicationIdSuffix : String? = null) {
    FREE(FlavorDimension.CONTENT_TYPE, ".free"),
    PAID(FlavorDimension.CONTENT_TYPE, ".paid"),
}

/**
 * Method that configures the flavors of each module and centralizes all the configuration in one place.
 */
fun configureFlavors(
    commonExtension: CommonExtension<*,*,*,*,*,*>,
    flavorConfigurationBlock : ProductFlavor.(flavor : CoreFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.CONTENT_TYPE.name
        productFlavors {
            CoreFlavor.values().forEach { coreFlavor ->
                productFlavors.create(coreFlavor.name) {
                    dimension = coreFlavor.dimension.name
                    flavorConfigurationBlock(coreFlavor)
                    if(this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if(coreFlavor.applicationIdSuffix != null) {
                            applicationIdSuffix = coreFlavor.applicationIdSuffix
                        }
                    }
                    when (coreFlavor) {
                        CoreFlavor.FREE -> buildConfigField("String", "QUIZ_API_URL", "\"https://opentdb.com/\"")
                        CoreFlavor.PAID -> buildConfigField("String", "QUIZ_API_URL", "\"https://opentdb.com/\"")
                    }
                }
            }
        }
    }
}