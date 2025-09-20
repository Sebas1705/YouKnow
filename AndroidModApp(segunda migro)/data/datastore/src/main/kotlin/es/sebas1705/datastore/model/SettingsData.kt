package es.sebas1705.datastore.model

/**
 * Data class to represent the settings data.
 *
 * @property firstTime [Boolean]: Indicates if it's the first time the app is opened.
 * @property musicVolume [Float]: Volume level for music (0.0 to 1.0).
 * @property soundVolume [Float]: Volume level for sound effects (0.0 to 1.0).
 * @property appContrast [Int]: Contrast setting for the app (e.g., 0 = Low, 1 = Medium, 2 = High).
 * @property language [Int]: Language setting (e.g., 0 = English, 1 = Spanish, etc.).
 * @property defaultSet [Boolean]: Indicates if the settings are set to default values.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class SettingsData(
    val firstTime: Boolean,
    val musicVolume: Float,
    val soundVolume: Float,
    val appContrast: Int,
    val language: Int,
    val defaultSet: Boolean
)
