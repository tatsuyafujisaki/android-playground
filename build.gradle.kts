buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.google.services)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.ktlint)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    // https://github.com/JLLeitschuh/ktlint-gradle/blob/master/plugin/src/main/kotlin/org/jlleitschuh/gradle/ktlint/KtlintExtension.kt
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        android.set(true)
        disabledRules.set(setOf("max-line-length"))
    }

//    repositories {
//        google()
//        mavenCentral()
//    }
}
