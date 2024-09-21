package es.sebas1705.youknowapp.ui.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val appShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

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