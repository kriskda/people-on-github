pluginManagement {
    repositories {
        google()
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

rootProject.name = "People on Github"
include(":app")
include(":domain")
include(":data")
include(":data:database")
include(":data:repository")
include(":data:network")
include(":domain")
