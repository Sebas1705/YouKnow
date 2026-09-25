package es.sebas1705.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import java.io.File
import java.util.Properties

/**
 * Enum class that represents the different dimensions of the flavors.
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
enum class FlavorDimension {
    CONTENT_TYPE
}

/**
 * The three environment profiles every Templetry catalog form ships
 * (ADR-0018), realized here as Android product flavors — the ecosystem's own
 * mechanism, alongside `buildConfigField`.
 *
 * `environment` is the canonical lowercase name every profile reports on
 * `BuildConfig.ENVIRONMENT`, kept separate from the flavor's own identifier
 * (`Development`/`Staging`/`Production`, matching this project's existing
 * capitalized flavor style) so the two can diverge without either breaking.
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
enum class CoreFlavor(
    val dimension: FlavorDimension,
    val environment: String,
    val applicationIdSuffix: String? = null,
    val verboseLogging: Boolean = false,
) {
    Development(FlavorDimension.CONTENT_TYPE, "development", ".dev", verboseLogging = true),
    Staging(FlavorDimension.CONTENT_TYPE, "staging", ".staging", verboseLogging = true),
    // YouKnow keeps the bare applicationId for production: it is the id already registered in
    // Firebase (App Distribution checks it) and the one the 1.0.0 release shipped with.
    Production(FlavorDimension.CONTENT_TYPE, "production", null, verboseLogging = false),
}

/**
 * Method that configures the flavors of each module and centralizes all the configuration in one place.
 *
 * Each flavor gets three `BuildConfig` fields: `ENVIRONMENT` (the canonical
 * name), `API_BASE_URL` (read from Doppler / `local.properties`, since it is the
 * one value a developer plausibly wants to override locally) and
 * `VERBOSE_LOGGING` (fixed per flavor — production never ships it on).
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    rootDir: File = File("."),
    flavorConfigurationBlock : ProductFlavor.(flavor : CoreFlavor) -> Unit = {}
) {
    // API_BASE_URL_<FLAVOR> comes from the environment (Doppler), then local.properties (written
    // by scripts/doppler-sync-local-properties.sh), then the legacy app/secrets.properties.
    fun load(file: File) = Properties().apply {
        if (file.exists()) file.inputStream().use { load(it) }
    }
    val localProperties = load(File(rootDir, "local.properties"))
    val secretsProperties = load(File(rootDir, "app/secrets.properties"))
    fun apiBaseUrl(flavor: CoreFlavor): String {
        val key = "API_BASE_URL_${flavor.name.uppercase()}"
        return System.getenv(key)?.takeIf { it.isNotBlank() }
            ?: localProperties.getProperty(key)
            ?: secretsProperties.getProperty(key)
            ?: ""
    }

    commonExtension.apply {
        flavorDimensions.add(FlavorDimension.CONTENT_TYPE.name)
        CoreFlavor.values().forEach { coreFlavor ->
            productFlavors.create(coreFlavor.name) {
                dimension = coreFlavor.dimension.name
                flavorConfigurationBlock(coreFlavor)
                if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                    if (coreFlavor.applicationIdSuffix != null) {
                        applicationIdSuffix = coreFlavor.applicationIdSuffix
                    }
                }
                buildConfigField("String", "ENVIRONMENT", "\"${coreFlavor.environment}\"")
                buildConfigField(
                    "String",
                    "API_BASE_URL",
                    "\"${apiBaseUrl(coreFlavor)}\""
                )
                buildConfigField("boolean", "VERBOSE_LOGGING", coreFlavor.verboseLogging.toString())
            }
        }
    }
}
