import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "es.sebas1705.build_logic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.org.jacoco.core)
}

gradlePlugin {
    plugins {
        ///////////////MODULES////////////////
        register("androidApplication") {
            id = "buildlogic.android.application"
            implementationClass = "AppConventionPlugin"
        }
        register("androidFeature") {
            id = "buildlogic.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("androidCore") {
            id = "buildlogic.android.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("androidData") {
            id = "buildlogic.android.data"
            implementationClass = "DataConventionPlugin"
        }
        register("androidDomain") {
            id = "buildlogic.android.domain"
            implementationClass = "DomainConventionPlugin"
        }

        //////////////LIBRARIES////////////////
        register("androidLibrary") {
            id = "buildlogic.android.library"
            implementationClass = "libraries.LibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "buildlogic.android.hilt"
            implementationClass = "libraries.HiltConventionPlugin"
        }
        register("androidFirebase") {
            id = "buildlogic.android.firebase"
            implementationClass = "libraries.FirebaseConventionPlugin"
        }
        register("androidRetrofit") {
            id = "buildlogic.android.retrofit"
            implementationClass = "libraries.RetrofitConventionPlugin"
        }
        register("androidCompose") {
            id = "buildlogic.android.compose"
            implementationClass = "libraries.ComposeConventionPlugin"
        }
        register("androidCredential") {
            id = "buildlogic.android.credential"
            implementationClass = "libraries.CredentialConventionPlugin"
        }
        register("androidDatastore") {
            id = "buildlogic.android.datastore"
            implementationClass = "libraries.DatastoreConventionPlugin"
        }
        register("androidCouchbase") {
            id = "buildlogic.android.couchbase"
            implementationClass = "libraries.CouchbaseConventionPlugin"
        }
        register("androidLifecycle") {
            id = "buildlogic.android.lifecycle"
            implementationClass = "libraries.LifecycleConventionPlugin"
        }
        register("androidMaterial") {
            id = "buildlogic.android.material"
            implementationClass = "libraries.MaterialConventionPlugin"
        }
        register("androidNavigation") {
            id = "buildlogic.android.navigation"
            implementationClass = "libraries.NavigationConventionPlugin"
        }
        register("androidUnitTest") {
            id = "buildlogic.android.unit.test"
            implementationClass = "libraries.UnitTestConventionPlugin"
        }
        register("androidJacoco") {
            id = "buildlogic.android.jacoco"
            implementationClass = "libraries.JacocoConventionPlugin"
        }
        register("androidDetekt") {
            id = "buildlogic.android.detekt"
            implementationClass = "libraries.DetektConventionPlugin"
        }

        //////////////FLAVORS////////////////
        register("androidFlavors") {
            id = "buildlogic.android.flavors"
            implementationClass = "FlavorsConventionPlugin"
        }
    }
}
