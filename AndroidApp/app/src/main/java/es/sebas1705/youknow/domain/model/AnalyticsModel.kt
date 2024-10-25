package es.sebas1705.youknow.domain.model
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

import android.os.Bundle

/**
 * Model to represent an analytics
 *
 * @property title [String]: Title of the analytics
 * @property analyticsString [List]<[Pair]<[String],[String]>>: List of analytics with String values
 * @property analyticsLong [List]<[Pair]<[String],[Long]>>: List of analytics with Long values
 * @property analyticsDouble [List]<[Pair]<[String],[Double]>>: List of analytics with Double values
 * @property analyticsBundle [List]<[Pair]<[String],[Bundle]>>: List of analytics with Bundle values
 * @property analyticsBundleArray [List]<[Pair]<[String],[Array]<[Bundle]>>>: List of analytics with Array of Bundle values
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class AnalyticsModel(
    val title: String,
    val analyticsString: List<Pair<String,String>> = emptyList(),
    val analyticsLong: List<Pair<String,Long>> = emptyList(),
    val analyticsDouble: List<Pair<String,Double>> = emptyList(),
    val analyticsBundle: List<Pair<String, Bundle>> = emptyList(),
    val analyticsBundleArray: List<Pair<String,Array<Bundle>>> = emptyList()
)
