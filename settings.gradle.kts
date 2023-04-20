pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // in case https://repo.maven.apache.org is down.
        maven(url = "https://repo1.maven.org")
    }
}

include(":app")
