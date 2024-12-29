pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

// https://developer.android.com/build/remote-repositories
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
