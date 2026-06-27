package libraries

import com.android.build.gradle.LibraryExtension
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.findByType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")

            extensions.configure(JacocoPluginExtension::class.java) {
                toolVersion = "0.8.14"
            }

            extensions.findByType(LibraryExtension::class.java)?.let { libExt ->
                libExt.testOptions.unitTests.isIncludeAndroidResources = true
            } ?: run {
                extensions.findByType(ApplicationExtension::class.java)?.let { appExt ->
                    appExt.testOptions.unitTests.isIncludeAndroidResources = true
                }
            }

            tasks.withType(Test::class.java).configureEach {
                extensions.configure(JacocoTaskExtension::class.java) {
                    isIncludeNoLocationClasses = true
                    excludes = listOf("jdk.internal.*")
                    setDestinationFile(layout.buildDirectory.file("jacoco/${name}.exec").get().asFile)
                }
            }

            val unitTestTask = tasks.findByName("testDebugUnitTest") as? Test

            val execFilesProvider = providers.provider {
                val dest = unitTestTask
                    ?.extensions
                    ?.findByType(JacocoTaskExtension::class)
                    ?.destinationFile
                if (dest != null) files(dest) else files()
            }

            val javaClasses = fileTree(layout.buildDirectory.dir("intermediates/javac/debug/classes"))
            val kotlinClasses = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug"))

            val excludes = listOf(
                "**/R.class",
                "**/R\$*.class",
                "**/BuildConfig.*",
                "**/Manifest*.*",
                "**/*Test*.*",
                "**/di/**",
                "**/*_Factory.*",
                "**/*_MembersInjector.*",
                "**/Hilt_*.*",
                "**/*JsonAdapter.*"
            )

            val classDirectoriesProvider = files(
                javaClasses.matching { exclude(excludes) },
                kotlinClasses.matching { exclude(excludes) }
            )

            val sourcesProvider = files("src/main/java", "src/main/kotlin")
            val compileDebugKotlin = tasks.findByName("compileDebugKotlin")
            val compileDebugJava = tasks.findByName("compileDebugJavaWithJavac")

            tasks.register("jacocoTestReport", JacocoReport::class.java) {
                group = "verification"
                description = "Generates Jacoco coverage reports for unit tests (debug)."

                if (unitTestTask != null) dependsOn(unitTestTask)
                if (compileDebugKotlin != null) dependsOn(compileDebugKotlin)
                if (compileDebugJava != null) dependsOn(compileDebugJava)

                classDirectories.setFrom(classDirectoriesProvider)
                sourceDirectories.setFrom(sourcesProvider)
                executionData.setFrom(execFilesProvider)

                reports {
                    xml.required.set(true)
                    html.required.set(true)
                    csv.required.set(false)
                    xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/test/jacocoTestReport.xml"))
                    html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/test/html"))
                }

                outputs.upToDateWhen { false }

                doFirst {
                    val missing = execFilesProvider.get().files.filterNot { it.exists() }
                    if (missing.isNotEmpty()) {
                        throw GradleException(
                            "Jacoco execution data not found for ${project.path}. Expected: ${missing.joinToString()}"
                        )
                    }
                }
            }

            tasks.register("jacocoTestCoverageVerification", JacocoCoverageVerification::class.java) {
                group = "verification"
                description = "Verifies Jacoco coverage with minimum thresholds (unit tests)."

                if (unitTestTask != null) dependsOn(unitTestTask)
                if (compileDebugKotlin != null) dependsOn(compileDebugKotlin)
                if (compileDebugJava != null) dependsOn(compileDebugJava)

                classDirectories.setFrom(classDirectoriesProvider)
                sourceDirectories.setFrom(sourcesProvider)
                executionData.setFrom(execFilesProvider)

                violationRules {
                    rule {
                        enabled = true
                        limit {
                            counter = "LINE"
                            value = "COVEREDRATIO"
                            minimum = "0.90".toBigDecimal()
                        }
                    }
                }

                doFirst {
                    val missing = execFilesProvider.get().files.filterNot { it.exists() }
                    if (missing.isNotEmpty()) {
                        throw GradleException(
                            "Jacoco execution data not found for ${project.path}. Expected: ${missing.joinToString()}"
                        )
                    }
                }
            }
        }
    }
}
