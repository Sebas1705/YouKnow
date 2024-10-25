package es.sebas1705.youknow.presentation.ui.theme
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

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Custom shapes to be used in the app.
 */
val appShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

/**
 * Custom shape that represents a curved shape.
 */
val CurvedShape: Shape = GenericShape { size, _ ->
    val width = size.width
    val height = size.height

    moveTo(x = width * 0f, y = height * 0f)
    quadraticTo( //Top
        x1 = width * 0.5f, y1 = height * -0.2f,
        x2 = width * 1f, y2 = height * 0f
    )
    quadraticTo( //Right
        x1 = width * 1f, y1 = height * 0.5f,
        x2 = width * 1f, y2 = height * 1f
    )
    quadraticTo( //Bottom
        x1 = width * 0.5f, y1 = height * 1.2f,
        x2 = width * 0f, y2 = height * 1f
    )
    quadraticTo( //Left
        x1 = width * -0f, y1 = height * 0.5f,
        x2 = width * 0f, y2 = height * 0f
    )
    close()
}