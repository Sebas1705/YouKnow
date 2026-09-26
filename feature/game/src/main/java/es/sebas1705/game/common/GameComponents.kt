package es.sebas1705.game.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.sebas1705.feature.games.R
import es.sebas1705.common.games.Difficulty
import es.sebas1705.ui.theme.makeTitle
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

/*
 * Shared look of the four games: the "hand-drawn notebook" style of the Play screen (thick purple
 * and brown strokes, Finger Paint headings, paper surfaces) turned into sticker-like cards with a
 * solid offset shadow, answer feedback, a top HUD, mode cards and a result screen.
 */

/** Result state of an answer option. */
enum class AnswerState { IDLE, CORRECT, WRONG, DIMMED }

/**
 * Colours the theme does not have: a muted green for correct answers that sits with the purple
 * primary and the brown tertiary, in light and dark variants.
 */
@Immutable
data class GamePalette(
    val success: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
)

@Composable
fun gamePalette(): GamePalette =
    if (MaterialTheme.colorScheme.surface.luminance() < 0.5f) GamePalette(
        success = Color(0xFF8FD3A4),
        successContainer = Color(0xFF1F4D30),
        onSuccessContainer = Color(0xFFC4F0D0),
    ) else GamePalette(
        success = Color(0xFF3E7D54),
        successContainer = Color(0xFFD9EEDD),
        onSuccessContainer = Color(0xFF12301D),
    )

/** Colour of a difficulty, from the palette instead of pure green/yellow/red. */
@Composable
fun Difficulty.tint(): Color = when (this) {
    Difficulty.EASY -> gamePalette().success
    Difficulty.MEDIUM -> MaterialTheme.colorScheme.tertiary
    Difficulty.HARD -> MaterialTheme.colorScheme.error
    else -> MaterialTheme.colorScheme.primary
}

/**
 * Sticker look: the shape filled with [container], a [border] stroke, and a solid copy of the shape
 * [shadowOffset] down-right in [shadow], like paper cut-outs on the page.
 */
fun Modifier.sticker(
    shape: Shape,
    container: Color,
    border: Color,
    shadow: Color = border,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.dp,
): Modifier = this
    .drawBehind {
        val outline = shape.createOutline(size, layoutDirection, this)
        val offset = shadowOffset.toPx()
        translate(offset, offset) { drawOutline(outline, shadow) }
    }
    .clip(shape)
    .background(container, shape)
    .border(borderWidth, border, shape)

/** A sticker-styled container. */
@Composable
fun StickerCard(
    modifier: Modifier = Modifier,
    container: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    border: Color = MaterialTheme.colorScheme.primary,
    shadow: Color = border,
    shadowOffset: Dp = 4.dp,
    shape: Shape = MaterialTheme.shapes.large,
    content: @Composable BoxScope.() -> Unit,
) = Box(
    modifier = modifier.sticker(shape, container, border, shadow, shadowOffset),
    content = content
)

/**
 * One answer of Quiz or Families. Presses sink into its shadow; after answering it turns green
 * (right) or red (wrong) and the others fade.
 */
@Composable
fun AnswerOption(
    text: String,
    state: AnswerState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    badge: String? = null,
    enabled: Boolean = true,
) {
    val palette = gamePalette()
    val scheme = MaterialTheme.colorScheme
    val container by animateColorAsState(
        when (state) {
            AnswerState.CORRECT -> palette.successContainer
            AnswerState.WRONG -> scheme.errorContainer
            else -> scheme.surfaceContainerLowest
        }, label = "container"
    )
    val accent by animateColorAsState(
        when (state) {
            AnswerState.CORRECT -> palette.success
            AnswerState.WRONG -> scheme.error
            AnswerState.DIMMED -> scheme.outline
            AnswerState.IDLE -> scheme.primary
        }, label = "accent"
    )
    val textColor = when (state) {
        AnswerState.CORRECT -> palette.onSuccessContainer
        AnswerState.WRONG -> scheme.onErrorContainer
        AnswerState.DIMMED -> scheme.onSurface.copy(alpha = 0.45f)
        AnswerState.IDLE -> scheme.onSurface
    }
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shadowOffset by animateDpAsState(if (pressed) 1.dp else 4.dp, label = "shadow")
    val pressShift by animateDpAsState(if (pressed) 3.dp else 0.dp, label = "shift")
    val pop by animateFloatAsState(
        if (state == AnswerState.CORRECT) 1.04f else 1f,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = "pop"
    )
    Box(
        modifier = modifier
            .offset(pressShift, pressShift)
            .graphicsLayer { scaleX = pop; scaleY = pop }
            .sticker(MaterialTheme.shapes.large, container, accent, accent, shadowOffset)
            .clickable(
                interactionSource = interaction,
                indication = ripple(),
                enabled = enabled && state == AnswerState.IDLE,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (badge != null) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(accent.copy(alpha = 0.14f), CircleShape)
                        .border(1.5.dp, accent, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = badge,
                        style = MaterialTheme.typography.titleMedium.makeTitle(),
                        color = accent
                    )
                }
                Spacer(Modifier.width(12.dp))
            }
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                textAlign = if (badge == null) TextAlign.Center else TextAlign.Start
            )
            when (state) {
                AnswerState.CORRECT -> Icon(Icons.Filled.Check, null, tint = accent)
                AnswerState.WRONG -> Icon(Icons.Filled.Close, null, tint = accent)
                else -> Unit
            }
        }
    }
}

/** Small rounded label, e.g. the difficulty of a question. */
@Composable
fun GameTag(text: String, color: Color, modifier: Modifier = Modifier) = Text(
    modifier = modifier
        .background(color.copy(alpha = 0.14f), CircleShape)
        .border(1.dp, color, CircleShape)
        .padding(horizontal = 10.dp, vertical = 3.dp),
    text = text,
    style = MaterialTheme.typography.labelMedium,
    fontWeight = FontWeight.Bold,
    color = color
)

/**
 * The in-game header: points (animated), round counter with progress bar, lives as hearts, and a
 * countdown bar that turns red when time is short.
 */
@Composable
fun GameHud(
    points: Int,
    modifier: Modifier = Modifier,
    round: Int? = null,
    rounds: Int? = null,
    lives: Int? = null,
    maxLives: Int? = null,
    timeLeft: Float? = null,
    timeTotal: Float? = null,
) {
    val scheme = MaterialTheme.colorScheme
    val shownPoints by animateIntAsState(points, tween(600), label = "points")
    StickerCard(
        modifier = modifier.fillMaxWidth(),
        shadow = scheme.tertiary,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.EmojiEvents, null, tint = scheme.tertiary)
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = shownPoints.toString(),
                        style = MaterialTheme.typography.titleLarge.makeTitle(),
                        color = scheme.primary
                    )
                }
                if (round != null && rounds != null) Text(
                    text = stringResource(R.string.feature_game_round_of, round, rounds),
                    style = MaterialTheme.typography.titleMedium.makeTitle(),
                    color = scheme.primary
                )
                if (lives != null) LivesRow(lives, maxLives)
            }
            if (round != null && rounds != null && rounds > 0) {
                val progress by animateFloatAsState(
                    (round - 1).coerceAtLeast(0) / rounds.toFloat(), tween(400), label = "progress"
                )
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(8.dp),
                    color = scheme.primary,
                    trackColor = scheme.primary.copy(alpha = 0.15f),
                    strokeCap = StrokeCap.Round,
                    drawStopIndicator = {}
                )
            }
            if (timeLeft != null && timeTotal != null && timeTotal > 0f) {
                val fraction = (timeLeft / timeTotal).coerceIn(0f, 1f)
                val barColor by animateColorAsState(
                    if (fraction < 0.3f) scheme.error else scheme.tertiary, label = "time"
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Timer, null, tint = barColor, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    LinearProgressIndicator(
                        progress = { fraction },
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp),
                        color = barColor,
                        trackColor = barColor.copy(alpha = 0.15f),
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {}
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = timeLeft.roundToInt().coerceAtLeast(0).toString(),
                        style = MaterialTheme.typography.titleSmall.makeTitle(),
                        color = barColor
                    )
                }
            }
        }
    }
}

@Composable
private fun LivesRow(lives: Int, maxLives: Int?) {
    val tint = MaterialTheme.colorScheme.tertiary
    if (maxLives != null && maxLives in 1..5) Row {
        (1..maxLives).forEach {
            val alive = it <= lives
            val scale by animateFloatAsState(
                if (alive) 1f else 0.8f, spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "heart"
            )
            Icon(
                imageVector = if (alive) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = if (alive) tint else tint.copy(alpha = 0.4f),
                modifier = Modifier
                    .size(22.dp)
                    .scale(scale)
            )
        }
    } else Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Favorite, null, tint = tint, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(4.dp))
        Text(
            text = lives.toString(),
            style = MaterialTheme.typography.titleMedium.makeTitle(),
            color = tint
        )
    }
}

/** A selectable game mode. */
@Immutable
data class ModeOption(
    val name: String,
    val icon: ImageVector,
    val description: String,
    val details: List<String>,
)

/**
 * Mode selection with the game's own illustration and title, and one card per mode that says what
 * it is and what it pays. The cards come in one after another.
 */
@Composable
fun ModeSelectionContent(
    title: String,
    illustration: Int,
    options: List<ModeOption>,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StickerCard(
            modifier = Modifier
                .rotate(-3f)
                .size(132.dp),
            border = scheme.tertiary,
            shadow = scheme.primary,
            shadowOffset = 5.dp
        ) {
            Image(
                painter = painterResource(illustration),
                contentDescription = title,
                modifier = Modifier.padding(6.dp)
            )
        }
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = title,
            style = MaterialTheme.typography.displaySmall.makeTitle(),
            color = scheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp),
            text = stringResource(R.string.feature_game_choose_mode),
            style = MaterialTheme.typography.titleMedium,
            color = scheme.onSurfaceVariant
        )
        options.forEachIndexed { index, option ->
            val appear = remember { Animatable(0f) }
            LaunchedEffect(Unit) {
                delay(70L * index)
                appear.animateTo(1f, tween(320, easing = FastOutSlowInEasing))
            }
            ModeCard(
                option = option,
                onClick = { onSelect(index) },
                modifier = Modifier
                    .padding(vertical = 7.dp)
                    .graphicsLayer {
                        alpha = appear.value
                        translationY = (1f - appear.value) * 40.dp.toPx()
                    }
            )
        }
    }
}

@Composable
private fun ModeCard(option: ModeOption, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val scheme = MaterialTheme.colorScheme
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shadowOffset by animateDpAsState(if (pressed) 1.dp else 4.dp, label = "shadow")
    val pressShift by animateDpAsState(if (pressed) 3.dp else 0.dp, label = "shift")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .offset(pressShift, pressShift)
            .sticker(MaterialTheme.shapes.large, scheme.surfaceContainerLowest, scheme.primary, scheme.primary, shadowOffset)
            .clickable(interaction, ripple(), role = Role.Button, onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(scheme.tertiary.copy(alpha = 0.14f), CircleShape)
                .border(1.5.dp, scheme.tertiary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(option.icon, null, tint = scheme.tertiary)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp)
        ) {
            Text(
                text = option.name,
                style = MaterialTheme.typography.titleLarge.makeTitle(),
                color = scheme.primary
            )
            Text(
                text = option.description,
                style = MaterialTheme.typography.bodyMedium,
                color = scheme.onSurfaceVariant
            )
            if (option.details.isNotEmpty()) Row(
                modifier = Modifier.padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                option.details.forEach { GameTag(it, scheme.primary) }
            }
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = scheme.primary)
    }
}

/** Formats a points multiplier like "1.5" or "1". */
fun Double.asMultiplier(): String =
    if (this % 1.0 == 0.0) toInt().toString() else toString()

/** One statistic of the result card. */
@Immutable
data class ResultStat(val label: String, val value: String, val color: Color)

/**
 * End of a game: a headline and stars from the score ([ratio] 0..1), the points counting up, the
 * stats as chips, and restart / exit.
 */
@Composable
fun GameResultContent(
    points: Int,
    ratio: Float,
    modeName: String,
    stats: List<ResultStat>,
    restartLabel: String,
    exitLabel: String,
    onRestart: () -> Unit,
    onExit: () -> Unit,
    restartIcon: ImageVector,
    exitIcon: ImageVector,
    modifier: Modifier = Modifier,
    extra: (@Composable () -> Unit)? = null,
) {
    val scheme = MaterialTheme.colorScheme
    val stars = when {
        ratio >= 0.999f -> 3
        ratio >= 0.7f -> 3
        ratio >= 0.4f -> 2
        ratio > 0f -> 1
        else -> 0
    }
    val headline = stringResource(
        when {
            ratio >= 0.999f -> R.string.feature_game_result_perfect
            ratio >= 0.7f -> R.string.feature_game_result_great
            ratio >= 0.4f -> R.string.feature_game_result_good
            else -> R.string.feature_game_result_retry
        }
    )
    val shownPoints = remember { Animatable(0f) }
    LaunchedEffect(points) { shownPoints.animateTo(points.toFloat(), tween(1_200, easing = FastOutSlowInEasing)) }

    StickerCard(
        modifier = modifier.fillMaxWidth(),
        shadow = scheme.tertiary,
        shadowOffset = 6.dp,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                (1..3).forEach { index ->
                    val earned = index <= stars
                    val appear = remember { Animatable(0f) }
                    LaunchedEffect(Unit) {
                        delay(250L * index)
                        appear.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
                    }
                    Icon(
                        imageVector = if (earned) Icons.Filled.Star else Icons.Filled.StarBorder,
                        contentDescription = null,
                        tint = if (earned) scheme.tertiary else scheme.outline,
                        modifier = Modifier
                            .size(if (index == 2) 56.dp else 44.dp)
                            .scale(appear.value)
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = headline,
                style = MaterialTheme.typography.headlineMedium.makeTitle(),
                color = scheme.primary,
                textAlign = TextAlign.Center
            )
            GameTag(modeName, scheme.tertiary, Modifier.padding(top = 6.dp))
            Text(
                modifier = Modifier.padding(top = 18.dp),
                text = shownPoints.value.roundToInt().toString(),
                style = MaterialTheme.typography.displayMedium.makeTitle(),
                color = scheme.tertiary
            )
            Text(
                text = stringResource(R.string.feature_game_points_earned),
                style = MaterialTheme.typography.bodyMedium,
                color = scheme.onSurfaceVariant
            )
            extra?.invoke()
            if (stats.isNotEmpty()) Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                stats.forEach { StatChip(it, Modifier.weight(1f)) }
            }
            GamePrimaryButton(
                text = restartLabel,
                icon = restartIcon,
                onClick = onRestart,
                modifier = Modifier.padding(top = 20.dp)
            )
            GameSecondaryButton(
                text = exitLabel,
                icon = exitIcon,
                onClick = onExit,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Composable
private fun StatChip(stat: ResultStat, modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .background(stat.color.copy(alpha = 0.12f), MaterialTheme.shapes.medium)
        .border(1.dp, stat.color.copy(alpha = 0.6f), MaterialTheme.shapes.medium)
        .padding(vertical = 8.dp, horizontal = 4.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stat.value,
        style = MaterialTheme.typography.titleLarge.makeTitle(),
        color = stat.color
    )
    Text(
        text = stat.label,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

/** Main call to action of the games, sticker-styled in the primary colour. */
@Composable
fun GamePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) = GameButton(text, onClick, modifier, icon, enabled, filled = true)

/** Secondary action (exit, change sign…), paper-coloured with a primary stroke. */
@Composable
fun GameSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) = GameButton(text, onClick, modifier, icon, enabled, filled = false)

@Composable
private fun GameButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    icon: ImageVector?,
    enabled: Boolean,
    filled: Boolean,
) {
    val scheme = MaterialTheme.colorScheme
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shadowOffset by animateDpAsState(if (pressed) 1.dp else 4.dp, label = "shadow")
    val pressShift by animateDpAsState(if (pressed) 3.dp else 0.dp, label = "shift")
    val container = if (filled) scheme.primary else scheme.surfaceContainerLowest
    val content = if (filled) scheme.onPrimary else scheme.primary
    Row(
        modifier = modifier
            .offset(pressShift, pressShift)
            .graphicsLayer { alpha = if (enabled) 1f else 0.5f }
            .sticker(CircleShape, container, scheme.primary, scheme.tertiary, shadowOffset)
            .clickable(interaction, ripple(), enabled = enabled, role = Role.Button, onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (icon != null) {
            Icon(icon, null, tint = content, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.makeTitle(),
            color = content
        )
    }
}

/** Status of one letter of the Word-Pass wheel. */
enum class LetterStatus { PENDING, CURRENT, CORRECT, WRONG }

/**
 * The Word-Pass "rosco": the letters of the round on a circle, green when guessed, red when
 * missed, and the current one highlighted and pulsing. [center] draws inside the ring.
 */
@Composable
fun LetterWheel(
    letters: List<Char>,
    statuses: List<LetterStatus>,
    modifier: Modifier = Modifier,
    maxSize: Dp = 320.dp,
    center: @Composable BoxScope.() -> Unit = {},
) {
    val palette = gamePalette()
    val scheme = MaterialTheme.colorScheme
    val pulse = rememberInfiniteTransition(label = "pulse")
    val pulseScale by pulse.animateFloat(
        initialValue = 1f, targetValue = 1.18f,
        animationSpec = infiniteRepeatable(tween(650), RepeatMode.Reverse), label = "pulseScale"
    )
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val side = min(maxWidth.value, maxSize.value).dp
        val count = letters.size.coerceAtLeast(1)
        val bubble = (side.value * 3.1f / (count + 6)).coerceIn(18f, 40f).dp
        val radius = (side - bubble) / 2
        Box(Modifier.size(side), contentAlignment = Alignment.Center) {
            center()
            letters.forEachIndexed { index, letter ->
                val angle = Math.toRadians(-90.0 + index * 360.0 / count)
                val status = statuses.getOrElse(index) { LetterStatus.PENDING }
                val (fill, stroke, text) = when (status) {
                    LetterStatus.CORRECT -> Triple(palette.success, palette.success, scheme.surface)
                    LetterStatus.WRONG -> Triple(scheme.error, scheme.error, scheme.onError)
                    LetterStatus.CURRENT -> Triple(scheme.tertiary, scheme.tertiary, scheme.onTertiary)
                    LetterStatus.PENDING -> Triple(scheme.surfaceContainerLowest, scheme.primary, scheme.primary)
                }
                Box(
                    modifier = Modifier
                        .offset(
                            x = radius * cos(angle).toFloat(),
                            y = radius * sin(angle).toFloat()
                        )
                        .size(bubble)
                        .scale(if (status == LetterStatus.CURRENT) pulseScale else 1f)
                        .background(fill, CircleShape)
                        .border(1.5.dp, stroke, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.uppercase(),
                        style = MaterialTheme.typography.labelLarge.makeTitle(),
                        fontWeight = FontWeight.Bold,
                        color = text
                    )
                }
            }
        }
    }
}

/**
 * Keeps the chosen answer on screen for a moment (green or red) before handing it to the game, so
 * the player sees the result. Returns the state of each option and the click handler.
 */
@Composable
fun rememberAnswerReveal(
    key: Any?,
    correctAnswer: String,
    onAnswer: (String) -> Unit,
    revealMillis: Long = 700,
): Pair<(String) -> AnswerState, (String) -> Unit> {
    var selected by remember(key) { mutableStateOf<String?>(null) }
    LaunchedEffect(key, selected) {
        val answer = selected ?: return@LaunchedEffect
        delay(revealMillis)
        onAnswer(answer)
    }
    val stateOf: (String) -> AnswerState = { option ->
        val chosen = selected
        when {
            chosen == null -> AnswerState.IDLE
            option == correctAnswer -> AnswerState.CORRECT
            option == chosen -> AnswerState.WRONG
            else -> AnswerState.DIMMED
        }
    }
    val choose: (String) -> Unit = { option -> if (selected == null) selected = option }
    return stateOf to choose
}
