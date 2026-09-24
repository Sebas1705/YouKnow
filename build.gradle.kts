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

tasks.register("coverageUnitTestAll") {
    group = "verification"
    description = "Runs unit tests and generates Jacoco reports for all subprojects that support it."

    // Depends on all subproject jacocoTestReport tasks when present.
    dependsOn(
        provider {
            subprojects
                .filter { it.tasks.names.contains("jacocoTestReport") }
                .map { "${it.path}:jacocoTestReport" }
        }
    )
}
