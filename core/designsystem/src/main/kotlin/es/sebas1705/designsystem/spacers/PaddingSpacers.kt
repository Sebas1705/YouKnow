package es.sebas1705.designsystem.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.ui.theme.Paddings.HugePadding
import es.sebas1705.ui.theme.Paddings.LargePadding
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding

/**
 * Padding spacers
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
object PaddingSpacers {

    /**
     * Smallest spacer
     *
     * @param vertical [Boolean]: Vertical
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Composable
    fun SmallestSpacer(vertical: Boolean = true) = Spacer(
        modifier =
        if (vertical)
            Modifier.height(SmallestPadding)
        else
            Modifier.width(SmallestPadding)
    )

    /**
     * Small spacer
     *
     * @param vertical [Boolean]: Vertical
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Composable
    fun SmallSpacer(vertical: Boolean = true) = Spacer(
        modifier =
        if (vertical)
            Modifier.height(SmallPadding)
        else
            Modifier.width(SmallPadding)
    )

    /**
     * Medium spacer
     *
     * @param vertical [Boolean]: Vertical
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Composable
    fun MediumSpacer(vertical: Boolean = true) = Spacer(
        modifier =
        if (vertical)
            Modifier.height(MediumPadding)
        else
            Modifier.width(MediumPadding)
    )

    /**
     * Large spacer
     *
     * @param vertical [Boolean]: Vertical
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Composable
    fun LargeSpacer(vertical: Boolean = true) = Spacer(
        modifier =
        if (vertical)
            Modifier.height(LargePadding)
        else
            Modifier.width(LargePadding)
    )

    /**
     * Huge spacer
     *
     * @param vertical [Boolean]: Vertical
     *
     * @since 1.0.0
     * @author Sebas1705 12/09/2025
     */
    @Composable
    fun HugeSpacer(vertical: Boolean = true) = Spacer(
        modifier =
        if (vertical)
            Modifier.height(HugePadding)
        else
            Modifier.width(HugePadding)
    )
}