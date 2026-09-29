package es.sebas1705.game.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.core.resources.Sounds
import es.sebas1705.designsystem.cards.IStickerCard
import es.sebas1705.designsystem.chips.ITag
import es.sebas1705.feature.games.R
import es.sebas1705.ui.theme.makeTitle
import kotlin.math.roundToInt

/**
 * Custom mode set-up: the game's illustration and title, one card per setting, and the start
 * button.
 *
 * @param title [String]: Name of the game.
 * @param illustration [Int]: Drawable of the game.
 * @param onStart () -> Unit: Starts the game with the chosen settings.
 * @param content Setting cards, usually [SetupSection]s.
 *
 * @since 1.2.1
 * @author Sebas1705 26/09/2026
 */
@Composable
fun CustomSetupContent(
    title: String,
    illustration: Int,
    onStart: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IStickerCard(
                modifier = Modifier
                    .rotate(-4f)
                    .size(84.dp),
                border = scheme.tertiary,
                shadow = scheme.primary
            ) {
                Image(
                    painter = painterResource(illustration),
                    contentDescription = title,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 18.dp)) {
                Text(
                    text = stringResource(R.string.feature_game_custom_title),
                    style = MaterialTheme.typography.headlineMedium.makeTitle(),
                    color = scheme.primary
                )
                Text(
                    text = "$title · " + stringResource(R.string.feature_game_custom_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = scheme.onSurfaceVariant
                )
            }
        }
        content()
        GamePrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            text = stringResource(R.string.feature_game_start_game),
            icon = Icons.Filled.PlayArrow,
            onClick = onStart
        )
    }
}

/**
 * One setting of the custom mode: a card with its name, the chosen value as a tag, and the control.
 *
 * @since 1.2.1
 * @author Sebas1705 26/09/2026
 */
@Composable
fun SetupSection(
    title: String,
    value: String?,
    modifier: Modifier = Modifier,
    valueColor: Color = MaterialTheme.colorScheme.tertiary,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scheme = MaterialTheme.colorScheme
    IStickerCard(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = MaterialTheme.typography.titleLarge.makeTitle(),
                    color = scheme.primary
                )
                if (value != null) ITag(value, valueColor)
            }
            content()
        }
    }
}

/**
 * Pill-shaped single choice. The selected pill is filled with its tint and carries a tick.
 *
 * @since 1.2.1
 * @author Sebas1705 26/09/2026
 */
@Composable
fun <T> ChoiceChips(
    options: List<T>,
    selected: T,
    label: @Composable (T) -> String,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier,
    tint: @Composable (T) -> Color = { MaterialTheme.colorScheme.primary },
) {
    val playSound = rememberGameSound()
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selected
            val color = tint(option)
            val fill by animateColorAsState(
                if (isSelected) color.copy(alpha = 0.2f) else Color.Transparent,
                label = "chip"
            )
            Row(
                modifier = Modifier
                    .background(fill, CircleShape)
                    .border(if (isSelected) 2.dp else 1.dp, if (isSelected) color else MaterialTheme.colorScheme.outline, CircleShape)
                    .clickable(role = Role.RadioButton) {
                        if (!isSelected) {
                            playSound(Sounds.RADIO_BUTTON)
                            onSelect(option)
                        }
                    }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isSelected) Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(16.dp)
                )
                Text(
                    text = label(option),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) color else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * A whole-number picker: a slider with one step per value and −/+ buttons for fine tuning.
 *
 * @since 1.2.1
 * @author Sebas1705 26/09/2026
 */
@Composable
fun CountSlider(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scheme = MaterialTheme.colorScheme
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onValueChange((value - 1).coerceIn(range)) },
                enabled = value > range.first
            ) {
                Icon(Icons.Filled.Remove, contentDescription = null, tint = scheme.primary)
            }
            Slider(
                modifier = Modifier.weight(1f),
                value = value.toFloat(),
                onValueChange = { onValueChange(it.roundToInt().coerceIn(range)) },
                valueRange = range.first.toFloat()..range.last.toFloat(),
                steps = (range.last - range.first - 1).coerceAtLeast(0)
            )
            IconButton(
                onClick = { onValueChange((value + 1).coerceIn(range)) },
                enabled = value < range.last
            ) {
                Icon(Icons.Filled.Add, contentDescription = null, tint = scheme.primary)
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp)) {
            Text(
                modifier = Modifier.weight(1f),
                text = range.first.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = scheme.onSurfaceVariant,
                textAlign = TextAlign.Start
            )
            Text(
                text = range.last.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = scheme.onSurfaceVariant
            )
        }
    }
}
