import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    id(libs.plugins.com.android.application.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
    id(libs.plugins.firebase.appdistribution.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.protobuf.get().pluginId)
    id("androidx.navigation.safeargs.kotlin") // https://developer.android.com/guide/navigation/use-graph/safe-args#enable
    id("kotlin-parcelize") // https://developer.android.com/kotlin/parcelize
    embeddedKotlin("plugin.serialization") // https://kotlinlang.org/docs/serialization.html#add-plugins-and-dependencies
    kotlin("android") // equivalent to id("kotlin-android"), https://developer.android.com/kotlin/add-kotlin#add, https://developer.android.com/build/migrate-to-kotlin-dsl#perform-refactoring
}

android {
    namespace = "com.github.tatsuyafujisaki.androidplayground"
    compileSdk = libs.versions.compile.target.sdk.get().toInt()
    defaultConfig {
        applicationId = "com.github.tatsuyafujisaki.androidplayground"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.compile.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true

            // https://github.com/Kotlin/kotlinx.serialization#android
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
                "kotlin-serialization.pro",
            )

            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com\"")

            signingConfig = signingConfigs.getByName("debug")

            // https://firebase.google.com/docs/app-distribution/android/distribute-gradle?apptype=aab#step_3_configure_your_distribution_properties
            firebaseAppDistribution {
                artifactType = "AAB"
                releaseNotes = "Release notes!"
                testers = "a@example.com, b@example.com"
            }
        }
    }

    // https://developer.android.com/build/jdks#toolchain
    // https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    androidTestImplementation(kotlin("test"))
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.fragment.testing)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
    implementation(libs.activity.compose)
    implementation(libs.activity)
    implementation(libs.animation)
    implementation(libs.appcompat)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.constraintlayout.compose)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.datastore.preferences)
    implementation(libs.datastore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.config)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)
    implementation(libs.foundation)
    implementation(libs.fragment.ktx)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.logging.interceptor)
    implementation(libs.material.icons.extended)
    implementation(libs.material3)
    implementation(libs.media3.exoplayer)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.paging.compose)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.recyclerview)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.room.runtime)
    implementation(libs.ui.tooling)
    implementation(libs.viewpager2)
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(platform(libs.retrofit.bom))
    ksp(libs.hilt.compiler.androidx)
    ksp(libs.hilt.compiler)
    ksp(libs.lifecycle.compiler)
    ksp(libs.room.compiler)
    testImplementation(kotlin("test"))
    testImplementation(libs.robolectric)
    testImplementation(libs.test.core.ktx)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

// https://docs.gradle.org/current/userguide/kotlin_dsl.html#extra_properties
tasks.register("printExtraPropertiesExample") {
    doLast {
        val apple = rootProject.extra["apple"] as? Map<*, *>
        val emoji = apple?.get("emoji") as? String
        val price = apple?.get("price") as? Int
        println(emoji)
        println(price)
    }
}
