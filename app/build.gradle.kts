import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import com.mikepenz.aboutlibraries.plugin.DuplicateMode
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.firebase.appdistribution)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.about.libraries)
    id("androidx.navigation.safeargs.kotlin") // https://developer.android.com/guide/navigation/use-graph/safe-args#enable
    id("kotlin-parcelize") // https://developer.android.com/kotlin/parcelize
    embeddedKotlin("plugin.serialization") // https://kotlinlang.org/docs/serialization.html#add-plugins-and-dependencies
}

// https://developer.android.com/studio/publish/app-signing#secure-shared-keystore
val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.github.tatsuyafujisaki.androidplayground"
    compileSdk = libs.versions.compile.target.sdk.get().toInt()
    // https://developer.android.com/build/jdks#toolchain
    // https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    defaultConfig {
        applicationId = "com.github.tatsuyafujisaki.androidplayground"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.compile.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // https://developer.android.com/build/build-variants#signing
    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(path = keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )

            signingConfig = signingConfigs.getByName("release")

            // https://firebase.google.com/docs/app-distribution/android/distribute-gradle#step_3_configure_your_distribution_properties
            // How to create and distribute an APK file:
            // `./gradlew assembleRelease appDistributionUploadRelease`
            firebaseAppDistribution {
                serviceCredentialsFile = "$rootDir/curious-llc-39ec9fbfbf5d.json"
                releaseNotes = "Release notes!"
                // If the email addresses below don't exist in the list of testers in the Firebase console, they will be automatically added.
                // https://console.firebase.google.com/project/curious-llc/appdistribution/app/android:com.github.tatsuyafujisaki.androidplayground/testers
                // Your app will still upload even if you don't specify a tester.
                // However, you must open Firebase Console and select the testers you want to distribute your app to.
                // https://console.firebase.google.com/project/curious-llc/appdistribution/app/android:com.github.tatsuyafujisaki.androidplayground/releases
                testers = "foo@example.com, bar@example.com"
            }
        }
    }

    buildFeatures {
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
    androidTestImplementation(kotlin("test-junit"))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.fragment.testing)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.about.libraries.compose)
    implementation(libs.about.libraries.core)
    implementation(libs.activity)
    implementation(libs.activity.compose)
    implementation(libs.animation)
    implementation(libs.appcompat)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout.compose)
    implementation(libs.coordinatorlayout)
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)
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
    implementation(libs.kotlinx.serialization.protobuf)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.navigation3)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.logging.interceptor)
    implementation(libs.material.icons.extended)
    implementation(libs.material3)
    implementation(libs.media3.common.ktx)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.ui.compose.material3)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation3.runtime)
    implementation(libs.navigation3.ui)
    implementation(libs.okhttp)
    implementation(libs.paging.compose)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.room.runtime)
    implementation(libs.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(platform(libs.retrofit.bom))
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.compiler.androidx)
    ksp(libs.room.compiler)
    testImplementation(kotlin("test-junit"))
}

aboutLibraries {
    // Specifies the configPath, where I can place additional library and license entries.
    // https://github.com/mikepenz/AboutLibraries?tab=readme-ov-file#gradle-plugin-configuration
    collect.configPath = file(path = "config")

    library {
        // Avoids displaying duplicate entries.
        duplicationMode = DuplicateMode.MERGE
    }
}

protobuf {
    protoc {
        artifact = libs.protoc.get().toString()
    }
    generateProtoTasks {
        all().configureEach {
            builtins.create("java").option("lite")
        }
    }
}
