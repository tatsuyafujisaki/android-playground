plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.github.tatsuyafujisaki.androidplayground"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    defaultConfig {
        applicationId = "com.github.tatsuyafujisaki.androidplayground"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
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
        isCoreLibraryDesugaringEnabled = true
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
}

dependencies {
    // https://github.com/google/desugar_jdk_libs/blob/master/CHANGELOG.md
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    /**
     * > Dependency on the standard library added by default
     * https://kotlinlang.org/docs/whatsnew14.html#dependency-on-the-standard-library-added-by-default
     */

    androidTestImplementation(libs.androidx.truth)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.idling.resource)
    androidTestImplementation(libs.espresso.intents)
    androidTestImplementation(libs.espresso.web)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.idling.concurrent)
    androidTestImplementation(libs.junit.ktx)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.fragment.testing)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.swiperefresh)
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
    implementation(libs.fragment.ktx)
    implementation(libs.glide)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.reactivestreams.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.logging.interceptor)
    implementation(libs.material)
    implementation(libs.material)
    implementation(libs.material.compose)
    implementation(libs.material.icons.extended)
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
    implementation(libs.ui.tooling.preview)
    implementation(libs.viewpager2)
    implementation(platform(libs.firebase.bom))
    kapt(libs.glide.compiler)
    kapt(libs.hilt.compiler)
    kapt(libs.lifecycle.common.java8)
    kapt(libs.room.compiler)
    kaptAndroidTest(libs.hilt.compiler)
    kaptTest(libs.hilt.compiler)
    testImplementation(libs.androidx.truth)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)
    testImplementation(libs.room.testing)
    testImplementation(libs.test.core.ktx)
    testImplementation(libs.truth)
}

kapt {
    correctErrorTypes = true
}
