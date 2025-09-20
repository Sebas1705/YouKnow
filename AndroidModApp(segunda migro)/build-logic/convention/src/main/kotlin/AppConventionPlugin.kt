import com.android.build.api.dsl.ApplicationExtension
import es.sebas1705.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * A plugin that configures an Android application project.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class AppConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.firebase.crashlytics")
                apply("com.google.gms.google-services")
                apply("org.jetbrains.kotlin.kapt")
                apply("org.jetbrains.kotlin.android")
                apply("buildlogic.android.hilt")
                apply("buildlogic.android.compose")
                apply("buildlogic.android.lifecycle")
                apply("buildlogic.android.flavors")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 36
                defaultConfig.minSdk = 31
            }
        }
    }
}