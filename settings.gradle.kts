pluginManagement {
    includeBuild("build-logic")
    repositories {
        // Putting less popular or slower repositories (like the Gradle Plugin Portal) at the end can speed up dependency resolution.
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Layout-Studio"

include(":app")

include(":core:analytics")
include(":core:coroutines")
include(":core:database")
include(":core:di")
include(":core:domain")
include(":core:firestore")
include(":core:navigation")
include(":core:ui")
include(":core:util")

include(":feature:home")
include(":feature:onboarding")
include(":feature:subscription")
include(":feature:chat_list")
include(":feature:people_list")
include(":feature:user_profile")
include(":feature:people_profile")
include(":feature:people")

include(":feature:settings_core")
include(":feature:main")

include(":feature:sync")

include(":feature:people_core")