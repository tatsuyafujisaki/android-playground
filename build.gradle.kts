buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.android.tools.build:gradle:7.3.0")
        classpath("com.google.gms:google-services:4.3.14")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
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
