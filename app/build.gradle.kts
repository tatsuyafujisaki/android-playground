plugins {
    // equivalent to kotlin("android")
    // https://kotlinlang.org/docs/multiplatform-mobile-understand-project-structure.html#android-application
    id("kotlin-android")
    @Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
    // equivalent to id("org.jetbrains.kotlin.plugin.serialization")
    // https://kotlinlang.org/docs/serialization.html#example-json-serialization
    kotlin("plugin.serialization") version libs.versions.kotlin
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
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
        getByName("release") {
            isMinifyEnabled = true

            // https://bumptech.github.io/glide/doc/download-setup.html#proguard
            // https://github.com/Kotlin/kotlinx.serialization#android
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
                "glide.pro",
                "kotlin-serialization.pro"
            )

            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com\"")

            /*
             * For debugging purposes
             */
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        // allWarningsAsErrors = true
        jvmTarget = "11"
    }
    buildFeatures {
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
    implementation(libs.animation)
    implementation(libs.appcompat)
    implementation(libs.coil.compose)
    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout.compose)
    implementation(libs.converter.moshi)
    implementation(libs.coordinatorlayout)
    implementation(libs.core.ktx)
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)
    implementation(libs.exoplayer)
    implementation(libs.firebase.messaging)
    implementation(libs.foundation)
    implementation(libs.fragment.ktx)
    implementation(libs.glide)
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
    implementation(libs.material3)
    implementation(libs.moshi.kotlin)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.paging.runtime)
    implementation(libs.play.core.ktx)
    implementation(libs.preference.ktx)
    implementation(libs.recyclerview)
    implementation(libs.room.ktx)
    implementation(libs.runtime.livedata)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.ui.tooling)
    implementation(libs.viewpager2)
    implementation(platform(libs.firebase.bom))
    ksp(libs.glide.compiler)
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
