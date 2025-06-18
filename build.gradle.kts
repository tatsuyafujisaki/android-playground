extra["apple"] = mapOf("emoji" to "🍎", "price" to 42)

buildscript {
    dependencies {
        // https://developer.android.com/guide/navigation/use-graph/safe-args#enable
        // https://developer.android.com/jetpack/androidx/releases/navigation#safe_args
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.firebase.appdistribution) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.protobuf) apply false
}
