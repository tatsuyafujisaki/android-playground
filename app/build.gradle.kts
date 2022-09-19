plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlin
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.github.tatsuyafujisaki.androidplayground"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")

    /**
     * > Dependency on the standard library added by default
     * https://kotlinlang.org/docs/whatsnew14.html#dependency-on-the-standard-library-added-by-default
     */

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${Versions.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:${Versions.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-intents:${Versions.espresso}")
    androidTestImplementation("androidx.test.espresso:espresso-web:${Versions.espresso}")
    androidTestImplementation("androidx.test.ext:truth:${Versions.androidxTest}")
    androidTestImplementation("androidx.test:core-ktx:${Versions.androidxTest}")
    androidTestImplementation("androidx.test:rules:${Versions.androidxTest}")
    androidTestImplementation("androidx.test:runner:${Versions.androidxTest}")
    androidTestImplementation("com.google.truth:truth:${Versions.truth}")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")
    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragment}")
    implementation("androidx.compose.animation:animation:1.2.1")
    implementation("androidx.compose.material:material-icons-extended:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.1")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragment}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
    implementation("androidx.paging:paging-runtime-ktx:${Versions.paging}")
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation("com.github.bumptech.glide:glide:${Versions.glide}")
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}")
    implementation("com.google.android.exoplayer:exoplayer:2.18.1")
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttp}") // CookieJar
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("com.google.android.play:core-ktx:1.8.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("io.coil-kt:coil-compose:2.2.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation(platform("com.google.firebase:firebase-bom:30.5.0"))
    kapt("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
    kapt("androidx.room:room-compiler:${Versions.room}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glide}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
    testImplementation("androidx.paging:paging-common-ktx:${Versions.paging}")
    testImplementation("androidx.room:room-testing:${Versions.room}")
    testImplementation("androidx.test.ext:truth:${Versions.androidxTest}")
    testImplementation("androidx.test:core-ktx:${Versions.androidxTest}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.robolectric:robolectric:4.8.2")
}
