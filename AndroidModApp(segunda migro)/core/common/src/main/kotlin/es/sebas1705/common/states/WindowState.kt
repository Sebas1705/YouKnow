package es.sebas1705.common.states


import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.sebas1705.common.theme.SizeType
import es.sebas1705.common.utlis.PreviewSettings

/**
 * Data class that represents the state of window and its properties for design purposes.
 *
 * @property widthDp Dp: Width of the window.
 * @property heightDp Dp: Height of the window.
 * @property widthType SizeType: Type of the width.
 * @property heightType SizeType: Type of the height.
 * @property isImeVisible Boolean: True if the IME is visible, false otherwise.
 * @property isPortrait Boolean: True if the window is in portrait mode, false otherwise.
 * @property backFill Int: Resource of the background image for the fill state.
 * @property backEmpty Int: Resource of the background image for the empty state.
 * @property isLandscapeAndIme Boolean: True if the window is in landscape mode and the IME is visible, false otherwise.
 *
 * @author: Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class WindowState(
    val widthDp: Dp,
    val heightDp: Dp,
    val widthType: SizeType,
    val heightType: SizeType,
    val isImeVisible: Boolean,
    val isPortrait: Boolean,
    val backFill: Int,
    val backEmpty: Int
) {
    val isLandscapeAndIme: Boolean = !isPortrait and isImeVisible

    companion object {
        fun default(): WindowState {
            return WindowState(
                widthDp = PreviewSettings.WIDTH.dp,
                heightDp = PreviewSettings.HEIGHT.dp,
                widthType = SizeType.COMPACT,
                heightType = SizeType.COMPACT,
                isImeVisible = false,
                isPortrait = true,
                backFill = es.sebas1705.core.resources.R.drawable.back_portrait_fill,
                backEmpty = es.sebas1705.core.resources.R.drawable.back_portrait_empty,
            )
        }
    }

    fun <T> sizeFilter(
        compactOpt: T,
        mediumOpt: T,
        expandedOpt: T
    ): T {
        return if (widthType == SizeType.EXPANDED || heightType == SizeType.EXPANDED) expandedOpt
        else if (widthType == SizeType.MEDIUM || heightType == SizeType.MEDIUM) mediumOpt
        else compactOpt
    }

    fun <T> widthFilter(
        compactOpt: T,
        mediumOpt: T,
        expandedOpt: T
    ): T = widthType
        .filter(compactOpt, mediumOpt, expandedOpt)

    fun <T> heightFilter(
        compactOpt: T,
        mediumOpt: T,
        expandedOpt: T
    ): T = heightType
        .filter(compactOpt, mediumOpt, expandedOpt)
}