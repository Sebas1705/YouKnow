package libraries

import es.sebas1705.convention.implementation
import es.sebas1705.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin that configures the dependencies for the Retrofit library.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class RetrofitConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.dependencies.apply {
            implementation(project.libs.findLibrary("okhttp").get())
            implementation(project.libs.findLibrary("retrofit").get())
            implementation(project.libs.findLibrary("retrofit-gson").get())
            implementation(project.libs.findLibrary("okhttp-logging-interceptor").get())
            implementation(project.libs.findLibrary("retrofit-converter-factory").get())
        }
    }
}