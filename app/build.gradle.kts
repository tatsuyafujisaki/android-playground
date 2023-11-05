plugins {
    id(libs.plugins.com.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    embeddedKotlin(libs.plugins.kotlin.serialization.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.androidx.navigation.safeargs.kotlin.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.oss.licenses.plugin.get().pluginId)
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
            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com\"")
        }
        getByName("release") {
            isMinifyEnabled = true

            // https://github.com/Kotlin/kotlinx.serialization#android
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
                "kotlin-serialization.pro"
            )

            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com\"")

            /**
             * For debugging purposes
             *
             * NB: Remove "isDebuggable = true" or OssLicensesMenuActivity will show an absent list.
             * https://github.com/google/play-services-plugins/issues/252
             */
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    composeOptions {
        // https://developer.android.com/jetpack/androidx/releases/compose-compiler
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    /**
     * > Dependency on the standard library added by default
     * https://kotlinlang.org/docs/whatsnew14.html#dependency-on-the-standard-library-added-by-default
     */

    // https://developer.android.com/jetpack/compose/setup#setup-compose
    // https://developer.android.com/jetpack/compose/setup#bom-version-mapping
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.fragment.testing)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.webview)
    implementation(libs.activity.compose)
    implementation(libs.activity)
    implementation(libs.animation)
    implementation(libs.appcompat)
    implementation(libs.coil.compose)
    implementation(libs.constraintlayout.compose)
    implementation(libs.constraintlayout)
    implementation(libs.converter.moshi)
    implementation(libs.coordinatorlayout)
    implementation(libs.core.ktx)
    implementation(libs.datastore.preferences)
    implementation(libs.datastore)
    implementation(libs.firebase.messaging)
    implementation(libs.foundation)
    implementation(libs.fragment.ktx)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.reactivestreams.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.logging.interceptor)
    implementation(libs.material.icons.extended)
    implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.media3.exoplayer)
    implementation(libs.moshi.kotlin)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.paging.runtime)
    implementation(libs.play.core.ktx)
    implementation(libs.play.services.oss.licenses)
    implementation(libs.preference.ktx)
    implementation(libs.recyclerview)
    implementation(libs.room.ktx)
    implementation(libs.runtime.livedata)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.ui.tooling)
    implementation(libs.viewpager2)
    implementation(platform(libs.firebase.bom))
    ksp(libs.hilt.compiler)
    ksp(libs.lifecycle.compiler)
    ksp(libs.room.compiler)
    kspAndroidTest(libs.hilt.compiler)
    kspTest(libs.hilt.compiler)
    testImplementation(kotlin("test"))
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    testImplementation(libs.room.testing)
    testImplementation(libs.test.core.ktx)
    androidTestImplementation(kotlin("test"))
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.idling.resource)
    androidTestImplementation(libs.espresso.intents)
    androidTestImplementation(libs.espresso.web)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.idling.concurrent)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.ui.test.junit4)
}
