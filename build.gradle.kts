extra["apple"] = mapOf("emoji" to "🍎", "price" to 42)

buildscript {
    dependencies {
        // https://developer.android.com/guide/navigation/use-graph/safe-args#enable
        // https://developer.android.com/jetpack/androidx/releases/navigation#safe_args
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    // You don't need to specify the version of Kotlin explicitly if all of your modules depend on org.jetbrains.kotlin.plugin.compose because it does so.
    // Optional: kotlin("android") version libs.versions.kotlin apply false
    // Optional: alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    // https://developer.android.com/build/releases/gradle-plugin#updating-plugin
    // alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.application) apply false // aka Android Gradle Plugin (AGP), https://developer.android.com/build/releases/gradle-plugin#updating-plugin
    alias(libs.plugins.compose.compiler) apply false // https://developer.android.com/develop/ui/compose/compiler#version-catalog
    alias(libs.plugins.google.services) apply false // https://firebase.google.com/docs/android/setup#add-config-file
    alias(libs.plugins.hilt) apply false // https://developer.android.com/training/dependency-injection/hilt-android#setup
    alias(libs.plugins.ksp) apply false // https://developer.android.com/build/migrate-to-ksp#add-ksp
    alias(libs.plugins.protobuf) apply false // https://developer.android.com/build/migrate-to-ksp#add-ksp
}
