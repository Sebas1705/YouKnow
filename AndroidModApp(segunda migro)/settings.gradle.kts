pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidModApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

include(":core")
include(":core:common")
include(":core:designsystem")
include(":core:resources")
include(":core:ui")

include(":data")
include(":data:analytics")
include(":data:authentication")
include(":data:firestore")
include(":data:realtime")
include(":data:room")
include(":data:datastore")
include(":data:files")
include(":data:retrofit")
include(":data:couchbase")
include(":data:repositories")

include(":domain")
include(":domain:managers")
include(":domain:models")
include(":domain:services")
include(":domain:mappers")
include(":domain:usescases")
include(":domain:usescases:families")
include(":domain:usescases:fill")
include(":domain:usescases:mysteryNumber")
include(":domain:usescases:quiz")
include(":domain:usescases:wordPass")
include(":domain:usescases:opendb")
include(":domain:usescases:analytics")
include(":domain:usescases:survey")
include(":domain:usescases:chat")
include(":domain:usescases:groups")
include(":domain:usescases:news")
include(":domain:usescases:settings")
include(":domain:usescases:auth")
include(":domain:usescases:user")

include(":feature")
include(":feature:main")
include(":feature:auth")
include(":feature:game")
include(":feature:guide")
include(":feature:home")
include(":feature:networkerror")
include(":feature:settings")
include(":feature:splash")
include(":feature:survey")