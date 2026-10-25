plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.gms) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.benmanes.versions)
    alias(libs.plugins.dependency.analyze) apply false
    jacoco
}

jacoco {
    toolVersion = libs.versions.orgJacocoCore.get()
}

// Secrets and config compiled into the APK (Firebase ids, Google Sign-In client, API URLs) and,
// in CI, the signing inputs. Modules ask for them with `secret("KEY", "default")`. Lookup order:
//   1. Environment variable — what `doppler run -- ./gradlew ...` injects (local and CI).
//   2. local.properties (gitignored) — what Android Studio reads; generated from Doppler by
//      scripts/doppler-sync-local-properties.sh, or written by hand.
//   3. The default.
// See docs/DISTRIBUTION.md for the full list of keys.
val localProperties = java.util.Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) file.inputStream().use { load(it) }
}
extra["secret"] = { key: String, default: String ->
    System.getenv(key)?.takeIf { it.isNotBlank() } ?: localProperties.getProperty(key) ?: default
}

tasks.named("dependencyUpdates").configure {
    group = "verification"
    description = "Generates a dependency update report used by the monthly maintenance workflow."
}

allprojects {
    configurations.all {
        resolutionStrategy {
            // One protobuf runtime for Firebase and DataStore, the same version protoc generates
            // for (`protobuf` in the version catalog explains why it stays on 3.x). The template
            // forced 3.19.4, older than what Firestore is built against and than the protoc 4.x
            // code DataStore generated. SettingsSerializerTest (data:datastore) round-trips through
            // whatever runtime resolves here.
            force("com.google.protobuf:protobuf-javalite:${libs.versions.protobuf.get()}")
        }
    }
    tasks.withType<Test>().configureEach {
        // Hilt generates unit-test sources in every module, so Gradle 9 sees "test sources" even
        // where there are no tests and fails the task; a module without tests is not an error here.
        failOnNoDiscoveredTests.set(false)
        // Pin the locale so number/date formatting assertions do not depend on the machine.
        systemProperty("user.language", "en")
        systemProperty("user.country", "US")
    }
}

// Subprojects are evaluated after the root, so anything that looks at their tasks has to be a
// provider resolved at execution time (the same trick coverageUnitTestAll already used).
val coveredSubprojects = provider {
    subprojects.filter { it.tasks.names.contains("jacocoTestReport") }
}

// Same generated-code exclusions as JacocoConventionPlugin, so the merged total is the sum of the
// per-module reports.
val mergedCoverageExcludes = listOf(
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "**/di/**",
    "**/*_Factory.*",
    "**/*_MembersInjector.*",
    "**/Hilt_*.*",
    "**/*JsonAdapter.*"
)

val jacocoMergedReport = tasks.register<JacocoReport>("jacocoMergedReport") {
    group = "verification"
    description = "Merges every module's debug unit-test coverage into build/reports/jacoco/merged."

    dependsOn(coveredSubprojects.map { projects -> projects.map { "${it.path}:jacocoTestReport" } })

    executionData.setFrom(
        coveredSubprojects.map { projects ->
            projects.map { p -> p.fileTree(p.layout.buildDirectory.dir("jacoco")) { include("testDebugUnitTest.exec") } }
        }
    )
    classDirectories.setFrom(
        coveredSubprojects.map { projects ->
            projects.map { p ->
                files(
                    p.fileTree(p.layout.buildDirectory.dir("tmp/kotlin-classes/debug")) { exclude(mergedCoverageExcludes) },
                    p.fileTree(p.layout.buildDirectory.dir("intermediates/javac/debug/classes")) { exclude(mergedCoverageExcludes) }
                )
            }
        }
    )
    sourceDirectories.setFrom(
        coveredSubprojects.map { projects ->
            projects.flatMap { p -> listOf(p.file("src/main/java"), p.file("src/main/kotlin")) }
        }
    )

    val xmlReport = layout.buildDirectory.file("reports/jacoco/merged/jacocoMergedReport.xml")
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
        xml.outputLocation.set(xmlReport)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/merged/html"))
    }
    outputs.upToDateWhen { false }

    doLast {
        // The report totals are the <counter> children of <report>; the validate workflow reads the
        // same LINE counter to post the number in the job summary.
        val report = javax.xml.parsers.DocumentBuilderFactory.newInstance().apply {
            setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        }.newDocumentBuilder().parse(xmlReport.get().asFile)
        val children = report.documentElement.childNodes
        for (i in 0 until children.length) {
            val node = children.item(i)
            if (node.nodeName == "counter" && node.attributes.getNamedItem("type").nodeValue == "LINE") {
                val covered = node.attributes.getNamedItem("covered").nodeValue.toInt()
                val total = covered + node.attributes.getNamedItem("missed").nodeValue.toInt()
                val percent = if (total == 0) 0.0 else covered * 100.0 / total
                logger.lifecycle("[jacoco] merged line coverage: %.2f%% (%d/%d lines)".format(percent, covered, total))
            }
        }
    }
}

tasks.register("coverageUnitTestAll") {
    group = "verification"
    description = "Runs unit tests and generates Jacoco reports (per module and merged) for all subprojects that support it."

    // Depends on all subproject jacocoTestReport tasks when present, plus the merged report.
    dependsOn(coveredSubprojects.map { projects -> projects.map { "${it.path}:jacocoTestReport" } })
    dependsOn(jacocoMergedReport)
}
